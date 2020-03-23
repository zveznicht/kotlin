/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.kotlin.idea.codeInsight.gradle;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.externalSystem.importing.ImportSpec;
import com.intellij.openapi.externalSystem.importing.ImportSpecBuilder;
import com.intellij.openapi.externalSystem.model.DataNode;
import com.intellij.openapi.externalSystem.model.ExternalProjectInfo;
import com.intellij.openapi.externalSystem.model.ProjectSystemId;
import com.intellij.openapi.externalSystem.model.project.ProjectData;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskId;
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationListenerAdapter;
import com.intellij.openapi.externalSystem.service.execution.ProgressExecutionMode;
import com.intellij.openapi.externalSystem.service.notification.ExternalSystemProgressNotificationManager;
import com.intellij.openapi.externalSystem.service.project.ExternalProjectRefreshCallback;
import com.intellij.openapi.externalSystem.service.project.ProjectDataManager;
import com.intellij.openapi.externalSystem.service.project.manage.ProjectDataManagerImpl;
import com.intellij.openapi.externalSystem.settings.AbstractExternalSystemSettings;
import com.intellij.openapi.externalSystem.settings.ExternalProjectSettings;
import com.intellij.openapi.externalSystem.util.ExternalSystemApiUtil;
import com.intellij.openapi.externalSystem.util.ExternalSystemUtil;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.util.Couple;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.util.BooleanFunction;
import com.intellij.util.containers.ConcurrentWeakKeySoftValueHashMap;
import com.intellij.util.containers.ContainerUtil;
import org.apache.log4j.Level;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.function.Predicate;

// part of com.intellij.openapi.externalSystem.test.ExternalSystemImportingTestCase
public abstract class ExternalSystemImportingTestCase extends ExternalSystemTestCase {

    public static final String MINIMAL_SUPPORTED_GRADLE_PLUGIN_VERSION = "1.3.0";
    public static final String LATEST_STABLE_GRADLE_PLUGIN_VERSION = "1.3.50";

    private Logger logger;

    protected List<LibraryOrderEntry> getModuleLibDeps(String moduleName, String depName) {
        return getModuleDep(moduleName, depName, LibraryOrderEntry.class);
    }

    @NotNull
    private List<ModuleOrderEntry> getModuleModuleDeps(@NotNull String moduleName, @NotNull String depName) {
        return getModuleDep(moduleName, depName, ModuleOrderEntry.class);
    }

    private List<String> collectModuleDepsNames(String moduleName, Predicate<OrderEntry> predicate) {
        List<String> actual = new ArrayList<>();

        for (OrderEntry e : getRootManager(moduleName).getOrderEntries()) {
            if (predicate.test(e)) {
                actual.add(e.getPresentableName());
            }
        }
        return actual;
    }

    private List<String> collectModuleDepsNames(String moduleName, Class clazz) {
        return collectModuleDepsNames(moduleName, entry -> clazz.isInstance(entry));
    }

    @NotNull
    private <T> List<T> getModuleDep(@NotNull String moduleName, @NotNull String depName, @NotNull Class<T> clazz) {
        List<T> deps = new ArrayList<>();

        for (OrderEntry e : getRootManager(moduleName).getOrderEntries()) {
            if (clazz.isInstance(e) && e.getPresentableName().equals(depName)) {
                deps.add((T)e);
            }
        }
        assertNotNull("Dependency not found: " + depName + "\namong: " + collectModuleDepsNames(moduleName, clazz), deps);
        return deps;
    }

    private ContentEntry getContentRoot(String moduleName) {
        ContentEntry[] ee = getContentRoots(moduleName);
        List<String> roots = new ArrayList<>();
        for (ContentEntry e : ee) {
            roots.add(e.getUrl());
        }

        String message = "Several content roots found: [" + StringUtil.join(roots, ", ") + "]";
        assertEquals(message, 1, ee.length);

        return ee[0];
    }

    private ContentEntry getContentRoot(String moduleName, String path) {
        for (ContentEntry e : getContentRoots(moduleName)) {
            if (e.getUrl().equals(VfsUtilCore.pathToUrl(path))) return e;
        }
        throw new AssertionError("content root not found");
    }

    public ContentEntry[] getContentRoots(String moduleName) {
        return getRootManager(moduleName).getContentEntries();
    }

    protected ModuleRootManager getRootManager(String module) {
        return ModuleRootManager.getInstance(getModule(module));
    }

    protected void ignoreData(BooleanFunction<DataNode<?>> booleanFunction, final boolean ignored) {
        final ExternalProjectInfo externalProjectInfo = ProjectDataManagerImpl.getInstance().getExternalProjectData(
                myProject, getExternalSystemId(), getCurrentExternalProjectSettings().getExternalProjectPath());
        assertNotNull(externalProjectInfo);

        final DataNode<ProjectData> projectDataNode = externalProjectInfo.getExternalProjectStructure();
        assertNotNull(projectDataNode);

        final Collection<DataNode<?>> nodes = ExternalSystemApiUtil.findAllRecursively(projectDataNode, booleanFunction);
        for (DataNode<?> node : nodes) {
            node.visit(dataNode -> dataNode.setIgnored(ignored));
        }
        ServiceManager.getService(ProjectDataManager.class).importData(projectDataNode, myProject, true);
    }

    protected void importProject(@NonNls String config) throws IOException {
        createProjectConfig(config);
        importProject();
    }

    protected void importProject() {
        doImportProject();
    }

    public boolean ensureIsNotGradleProxyObject(
            Object o,
            Map<Object, Object> referencingObjects,
            Map<Object, String> referencingFieldNames
    ) {
        if (!(o instanceof Proxy)) {
            return true;
        }
        StringBuilder errMessage = new StringBuilder();
        errMessage.append(String.format(
                "Object [%s] seems to be a referenced gradle tooling api object. (it may lead to memory leaks during import) Referencing path: ",
                o));
        while (o != null) {
            errMessage.append(String.format("[%s].[%s] type: %s <-\r\n", o, referencingFieldNames.get(o), o.getClass().toString()));
            o = referencingObjects.get(o);
        }
        System.err.println(errMessage.toString());
        //TODO uncomment after fixing IDEA-207782
        //fail(errMessage.toString());
        return false;
    }

    private boolean shouldBeProcessed(Object toProcess, Set<Object> processed) {
        return toProcess != null && !processed.contains(toProcess);
    }

    public void inspectForGradleMemoryLeaks(DataNode<ProjectData> externalProject) {
        // Static logger initialisation should not be used because logger factory may be changed on test.setUp
        if (logger == null) {
            logger = Logger.getInstance(this.getClass().getName());
            logger.setLevel(Level.INFO);//not required
        }
        long start = System.currentTimeMillis();
        Set<Object> processed = new HashSet<>();
        Queue<Object> toProcess = new LinkedList<>();
        Map<Object, Object> referencingObjects = new HashMap<>();
        Map<Object, String> referencingFieldNames = new HashMap<>();
        Set<Field> modifiedFields = new HashSet<>();
        toProcess.add(externalProject);
        try {
            while (!toProcess.isEmpty()) {
                Object nextObject = toProcess.poll();
                processed.add(nextObject);
                if (!ensureIsNotGradleProxyObject(nextObject, referencingObjects, referencingFieldNames)) {
                    break;
                }

                for (Field field : nextObject.getClass().getDeclaredFields()) {
                    try {

                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                            modifiedFields.add(field);
                        }
                        final Object fieldValue = field.get(nextObject);
                        if (fieldValue == null || fieldValue.getClass().isPrimitive()) {
                            continue;
                        }
                        if (fieldValue instanceof Collection) {
                            for (Object o : (Collection) fieldValue) {
                                saveToProcessIfRequired(processed, toProcess, referencingObjects, referencingFieldNames, nextObject, o,
                                                        field.getName());
                            }
                        }
                        else if (fieldValue.getClass().isArray()) {
                            for (int i = 0; i < Array.getLength(fieldValue); i++) {
                                Object o = Array.get(fieldValue, i);
                                saveToProcessIfRequired(processed, toProcess, referencingObjects, referencingFieldNames, nextObject, o,
                                                        field.getName());
                            }
                        }
                        else if (fieldValue instanceof Map && ! (fieldValue instanceof ConcurrentWeakKeySoftValueHashMap)) {
                            for (Map.Entry e : ((Map<Object, Object>) fieldValue).entrySet()) {
                                saveToProcessIfRequired(processed, toProcess, referencingObjects, referencingFieldNames, nextObject, e.getKey(),
                                                        field.getName());
                                saveToProcessIfRequired(processed, toProcess, referencingObjects, referencingFieldNames, nextObject, e.getValue(),
                                                        "value" + field.getName());
                            }
                        }
                        else {
                            saveToProcessIfRequired(processed, toProcess, referencingObjects, referencingFieldNames, nextObject, fieldValue,
                                                    field.getName());
                        }
                    }
                    catch (IllegalAccessException e) {
                        fail(e.getMessage());
                    }
                }
            }
        }
        finally {
            for (Field f : modifiedFields) {
                f.setAccessible(false);
            }
            logger.info(
                    String.format("Memory leak tracker has finished. Number of processed objects = %s. Duration = %s ms.", processed.size(),
                                  (System.currentTimeMillis() - start)));
        }
    }

    private void saveToProcessIfRequired(
            Set<Object> processed,
            Queue<Object> toProcess,
            Map<Object, Object> referrers,
            Map<Object, String> referencingFieldNames,
            Object referringObject,
            Object o,
            String fieldName
    ) {
        if (shouldBeProcessed(o, processed)) {
            toProcess.add(o);
            referencingFieldNames.put(o, fieldName);
            referrers.put(o, referringObject);
        }
    }

    private void doImportProject() {
        AbstractExternalSystemSettings systemSettings = ExternalSystemApiUtil.getSettings(myProject, getExternalSystemId());
        final ExternalProjectSettings projectSettings = getCurrentExternalProjectSettings();
        projectSettings.setExternalProjectPath(getProjectPath());
        //noinspection unchecked
        Set<ExternalProjectSettings> projects = new HashSet<>(systemSettings.getLinkedProjectsSettings());
        projects.remove(projectSettings);
        projects.add(projectSettings);
        //noinspection unchecked
        systemSettings.setLinkedProjectsSettings(projects);

        final Ref<Couple<String>> error = Ref.create();
        ImportSpec importSpec = createImportSpec();
        ExternalProjectRefreshCallback callback = importSpec.getCallback();
        if (callback == null || callback instanceof ImportSpecBuilder.DefaultProjectRefreshCallback) {
            importSpec = new ImportSpecBuilder(importSpec).callback(new ExternalProjectRefreshCallback() {
                @Override
                public void onSuccess(@Nullable final DataNode<ProjectData> externalProject) {
                    if (externalProject == null) {
                        System.err.println("Got null External project after import");
                        return;
                    }
                    inspectForGradleMemoryLeaks(externalProject);
                    ServiceManager.getService(ProjectDataManager.class).importData(externalProject, myProject, true);
                    System.out.println("External project was successfully imported");
                }

                @Override
                public void onFailure(@NotNull String errorMessage, @Nullable String errorDetails) {
                    error.set(Couple.of(errorMessage, errorDetails));
                }
            }).build();
        }

        ExternalSystemProgressNotificationManager notificationManager =
                ServiceManager.getService(ExternalSystemProgressNotificationManager.class);
        ExternalSystemTaskNotificationListenerAdapter listener = new ExternalSystemTaskNotificationListenerAdapter() {
            @Override
            public void onTaskOutput(@NotNull ExternalSystemTaskId id, @NotNull String text, boolean stdOut) {
                if (StringUtil.isEmptyOrSpaces(text)) return;
                (stdOut ? System.out : System.err).print(text);
            }
        };
        notificationManager.addNotificationListener(listener);
        try {
            ExternalSystemUtil.refreshProjects(importSpec);
        }
        finally {
            notificationManager.removeNotificationListener(listener);
        }

        if (!error.isNull()) {
            handleImportFailure(error.get().first, error.get().second);
        }
    }

    protected void handleImportFailure(@NotNull String errorMessage, @Nullable String errorDetails) {
        String failureMsg = "Import failed: " + errorMessage;
        if (StringUtil.isNotEmpty(errorDetails)) {
            failureMsg += "\nError details: \n" + errorDetails;
        }
        fail(failureMsg);
    }

    protected ImportSpec createImportSpec() {
        ImportSpecBuilder importSpecBuilder = new ImportSpecBuilder(myProject, getExternalSystemId())
                .use(ProgressExecutionMode.MODAL_SYNC)
                .forceWhenUptodate();
        return importSpecBuilder.build();
    }

    protected abstract ExternalProjectSettings getCurrentExternalProjectSettings();

    protected abstract ProjectSystemId getExternalSystemId();

    protected void assertModuleModuleDepScope(String moduleName, String depName, DependencyScope... scopes) {
        List<ModuleOrderEntry> deps = getModuleModuleDeps(moduleName, depName);
        Set<DependencyScope> actualScopes = new HashSet<DependencyScope>();
        for (ModuleOrderEntry dep : deps) {
            actualScopes.add(dep.getScope());
        }
        HashSet<DependencyScope> expectedScopes = new HashSet<DependencyScope>(Arrays.asList(scopes));
        assertEquals("Dependency '" + depName + "' for module '" + moduleName + "' has unexpected scope",
                     expectedScopes, actualScopes);
    }

    protected void assertNoModuleDepForModule(String moduleName, String depName) {
        assertEmpty("No dependency '" + depName + "' was expected", collectModuleDeps(moduleName, depName, ModuleOrderEntry.class));
    }

    protected void assertNoLibraryDepForModule(String moduleName, String depName) {
        assertEmpty("No dependency '" + depName + "' was expected", collectModuleDeps(moduleName, depName, LibraryOrderEntry.class));
    }

    @NotNull
    private <T> List<T> collectModuleDeps(@NotNull String moduleName, @NotNull String depName, @NotNull Class<T> clazz) {
        List<T> deps = ContainerUtil.newArrayList();

        for (OrderEntry e : getRootManager(moduleName).getOrderEntries()) {
            if (clazz.isInstance(e) && e.getPresentableName().equals(depName)) {
                deps.add((T) e);
            }
        }

        return deps;
    }
 }
