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

import com.intellij.compiler.CompilerTestUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.StdModuleTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.impl.VfsRootAccess;
import com.intellij.testFramework.EdtTestUtil;
import com.intellij.testFramework.PsiTestUtil;
import com.intellij.testFramework.RunAll;
import com.intellij.testFramework.UsefulTestCase;
import com.intellij.testFramework.fixtures.IdeaProjectTestFixture;
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.ExceptionUtil;
import com.intellij.util.PathUtil;
import com.intellij.util.SmartList;
import com.intellij.util.io.PathKt;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.SystemIndependent;
import org.junit.After;
import org.junit.Before;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

// part of com.intellij.openapi.externalSystem.test.ExternalSystemTestCase
public abstract class ExternalSystemTestCase extends UsefulTestCase {

    private File ourTempDir;

    protected IdeaProjectTestFixture myTestFixture;
    protected Project myProject;
    protected File myTestDir;
    protected VirtualFile myProjectRoot;
    protected VirtualFile myProjectConfig;
    protected List<VirtualFile> myAllConfigs = new ArrayList<>();
    protected boolean useProjectTaskManager;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        ensureTempDirCreated();

        myTestDir = new File(ourTempDir, getTestName(false));
        FileUtil.ensureExists(myTestDir);

        setUpFixtures();
        myProject = myTestFixture.getProject();

        EdtTestUtil.runInEdtAndWait(() -> ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                setUpInWriteAction();
            }
            catch (Throwable e) {
                try {
                    tearDown();
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
                throw new RuntimeException(e);
            }
        }));

        List<String> allowedRoots = new ArrayList<>();
        collectAllowedRoots(allowedRoots);
        if (!allowedRoots.isEmpty()) {
            VfsRootAccess.allowRootAccess(myTestFixture.getTestRootDisposable(), ArrayUtilRt.toStringArray(allowedRoots));
        }

        CompilerTestUtil.enableExternalCompiler();
    }

    protected void collectAllowedRoots(List<String> roots) {
    }

    public static Collection<String> collectRootsInside(String root) {
        final List<String> roots = new SmartList<>();
        roots.add(root);
        FileUtil.processFilesRecursively(new File(root), file -> {
            try {
                String path = file.getCanonicalPath();
                if (!FileUtil.isAncestor(path, path, false)) {
                    roots.add(path);
                }
            }
            catch (IOException ignore) {
            }
            return true;
        });

        return roots;
    }

    private void ensureTempDirCreated() throws IOException {
        if (ourTempDir != null) return;

        ourTempDir = new File(FileUtil.getTempDirectory(), getTestsTempDir());
        FileUtil.delete(ourTempDir);
        FileUtil.ensureExists(ourTempDir);
    }

    protected String getTestsTempDir() {
        return ".";
    }

    protected void setUpFixtures() throws Exception {
        myTestFixture = IdeaTestFixtureFactory.getFixtureFactory().createFixtureBuilder(getName(), useDirectoryBasedStorageFormat()).getFixture();
        myTestFixture.setUp();
    }

    protected boolean useDirectoryBasedStorageFormat() {
        return false;
    }

    protected void setUpInWriteAction() throws Exception {
        File projectDir = new File(myTestDir, "project");
        FileUtil.ensureExists(projectDir);
        myProjectRoot = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(projectDir);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        new RunAll(
                () -> {
                    if (myProject != null && !myProject.isDisposed()) {
                        PathKt.delete(ProjectUtil.getExternalConfigurationDir(myProject));
                    }
                },
                () -> EdtTestUtil.runInEdtAndWait(() -> CompilerTestUtil.disableExternalCompiler(myProject)),
                () -> EdtTestUtil.runInEdtAndWait(() -> tearDownFixtures()),
                () -> myProject = null,
                () -> PathKt.delete(myTestDir.toPath()),
                () -> super.tearDown(),
                () -> resetClassFields(getClass())
        ).run();
    }

    protected void tearDownFixtures() {
        if (myTestFixture != null) {
            try {
                myTestFixture.tearDown();
            }
            catch (Exception ignored) {
            }
        }
        myTestFixture = null;
    }

    private void resetClassFields(final Class<?> aClass) {
        if (aClass == null) return;

        final Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            final int modifiers = field.getModifiers();
            if ((modifiers & Modifier.FINAL) == 0
                && (modifiers & Modifier.STATIC) == 0
                && !field.getType().isPrimitive()) {
                field.setAccessible(true);
                try {
                    field.set(this, null);
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if (aClass == ExternalSystemTestCase.class) return;
        resetClassFields(aClass.getSuperclass());
    }

    @Override
    protected void runTest() throws Throwable {
        try {
            if (runInWriteAction()) {
                try {
                    WriteAction.runAndWait(() -> super.runTest());
                }
                catch (Throwable throwable) {
                    ExceptionUtil.rethrowAllAsUnchecked(throwable);
                }
            }
            else {
                super.runTest();
            }
        }
        catch (Exception throwable) {
            Throwable each = throwable;
            do {
                if (each instanceof HeadlessException) {
                    printIgnoredMessage("Doesn't work in Headless environment");
                    return;
                }
            }
            while ((each = each.getCause()) != null);
            throw throwable;
        }
    }

    @Override
    protected void invokeTestRunnable(@NotNull Runnable runnable) {
        runnable.run();
    }

    protected boolean runInWriteAction() {
        return false;
    }

    protected static String getRoot() {
        if (SystemInfo.isWindows) return "c:";
        return "";
    }

    protected String getProjectPath() {
        return myProjectRoot.getPath();
    }

    protected String getParentPath() {
        return myProjectRoot.getParent().getPath();
    }

    @SystemIndependent
    protected String path(@NotNull String relativePath) {
        return PathUtil.toSystemIndependentName(file(relativePath).getPath());
    }

    protected File file(@NotNull String relativePath) {
        return new File(getProjectPath(), relativePath);
    }

    protected Module createModule(String name) {
        return createModule(name, StdModuleTypes.JAVA);
    }

    protected Module createModule(final String name, final ModuleType type) {
        try {
            return WriteCommandAction.writeCommandAction(myProject).compute(() -> {
                VirtualFile f = createProjectSubFile(name + "/" + name + ".iml");
                Module module = ModuleManager.getInstance(myProject).newModule(f.getPath(), type.getId());
                PsiTestUtil.addContentRoot(module, f.getParent());
                return module;
            });
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected VirtualFile createProjectConfig(@NonNls String config) {
        return myProjectConfig = createConfigFile(myProjectRoot, config);
    }

    protected VirtualFile createConfigFile(final VirtualFile dir, String config) {
        final String configFileName = getExternalSystemConfigFileName();
        VirtualFile configFile;
        try {
            configFile = WriteAction.computeAndWait(() -> {
                VirtualFile file = dir.findChild(configFileName);
                return file == null ? dir.createChildData(null, configFileName) : file;
            });
            myAllConfigs.add(configFile);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        setFileContent(configFile, config, true);
        return configFile;
    }

    protected abstract String getExternalSystemConfigFileName();

    protected VirtualFile createProjectSubFile(String relativePath) throws IOException {
        File f = new File(getProjectPath(), relativePath);
        FileUtil.ensureExists(f.getParentFile());
        FileUtil.ensureCanCreateFile(f);
        final boolean created = f.createNewFile();
        if (!created && !f.exists()) {
            throw new AssertionError("Unable to create the project sub file: " + f.getAbsolutePath());
        }
        return LocalFileSystem.getInstance().refreshAndFindFileByIoFile(f);
    }

     protected VirtualFile createProjectSubFile(String relativePath, String content) throws IOException {
        VirtualFile file = createProjectSubFile(relativePath);
        setFileContent(file, content, false);
        return file;
    }

    protected Module getModule(final String name) {
        return getModule(myProject, name);
    }

    protected Module getModule(Project project, String name) {
        Module m = ReadAction.compute(() -> ModuleManager.getInstance(project).findModuleByName(name));
        assertNotNull("Module " + name + " not found", m);
        return m;
    }

    protected static void setFileContent(final VirtualFile file, final String content, final boolean advanceStamps) {
        try {
            WriteAction.runAndWait(() -> {
                if (advanceStamps) {
                    file.setBinaryContent(content.getBytes(StandardCharsets.UTF_8), -1, file.getTimeStamp() + 4000);
                }
                else {
                    file.setBinaryContent(content.getBytes(StandardCharsets.UTF_8), file.getModificationStamp(), file.getTimeStamp());
                }
            });
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printIgnoredMessage(String message) {
        String toPrint = "Ignored";
        if (message != null) {
            toPrint += ", because " + message;
        }
        toPrint += ": " + getClass().getSimpleName() + "." + getName();
        System.out.println(toPrint);
    }
}