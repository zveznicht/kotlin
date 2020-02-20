package org.jetbrains.kotlin.tools.projectWizard.moduleConfigurators

import org.jetbrains.kotlin.tools.projectWizard.core.*
import org.jetbrains.kotlin.tools.projectWizard.core.entity.PluginSettingReference
import org.jetbrains.kotlin.tools.projectWizard.core.entity.SettingType
import org.jetbrains.kotlin.tools.projectWizard.core.entity.reference
import org.jetbrains.kotlin.tools.projectWizard.core.service.kotlinVersionKind
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.*
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.AndroidConfigIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.BuildScriptDependencyIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.BuildScriptRepositoryIR
import org.jetbrains.kotlin.tools.projectWizard.ir.buildsystem.gradle.RawGradleIR
import org.jetbrains.kotlin.tools.projectWizard.library.MavenArtifact
import org.jetbrains.kotlin.tools.projectWizard.moduleConfigurators.AndroidModuleConfigurator.Companion.createRepositories
import org.jetbrains.kotlin.tools.projectWizard.plugins.AndroidPlugin
import org.jetbrains.kotlin.tools.projectWizard.plugins.kotlin.ModuleConfigurationData
import org.jetbrains.kotlin.tools.projectWizard.plugins.kotlin.ModuleType
import org.jetbrains.kotlin.tools.projectWizard.plugins.templates.TemplatesPlugin
import org.jetbrains.kotlin.tools.projectWizard.settings.JavaPackage
import org.jetbrains.kotlin.tools.projectWizard.settings.buildsystem.DefaultRepository
import org.jetbrains.kotlin.tools.projectWizard.settings.buildsystem.Module
import org.jetbrains.kotlin.tools.projectWizard.settings.buildsystem.Repository
import org.jetbrains.kotlin.tools.projectWizard.settings.javaPackage
import org.jetbrains.kotlin.tools.projectWizard.settings.version.Version
import org.jetbrains.kotlin.tools.projectWizard.templates.FileTemplate
import org.jetbrains.kotlin.tools.projectWizard.templates.FileTemplateDescriptor
import java.nio.file.Path

object AndroidSinglePlatformModuleConfigurator :
    SinglePlatformModuleConfigurator,
    AndroidModuleConfigurator {
    override val id = "android"
    override val suggestedModuleName = "android"
    override val text = "Android"


    override fun WritingContext.runArbitraryTask(
        configurationData: ModuleConfigurationData,
        module: Module,
        modulePath: Path
    ): TaskResult<Unit> = computeM {
        val javaPackage = module.javaPackage(configurationData.pomIr)
        TemplatesPlugin::addFileTemplates.execute(
            listOf(
                FileTemplate(FileTemplateDescriptors.activityMainXml, modulePath, "package" to javaPackage),
                FileTemplate(FileTemplateDescriptors.androidManifestXml, modulePath, "package" to javaPackage),
                FileTemplate(FileTemplateDescriptors.mainActivityKt(javaPackage), modulePath, "package" to javaPackage)
            )
        )
    }

    override fun ReadingContext.createModuleIRs(configurationData: ModuleConfigurationData, module: Module): List<BuildSystemIR> =
        buildList {
            +ArtifactBasedLibraryDependencyIR(
                MavenArtifact(DefaultRepository.GOOGLE, "androidx.appcompat", "appcompat"),
                version = Version.fromString("1.1.0"),
                dependencyType = DependencyType.MAIN
            )

            +ArtifactBasedLibraryDependencyIR(
                MavenArtifact(DefaultRepository.GOOGLE, "androidx.core", "core-ktx"),
                version = Version.fromString("1.1.0"),
                dependencyType = DependencyType.MAIN
            )

            +ArtifactBasedLibraryDependencyIR(
                MavenArtifact(DefaultRepository.GOOGLE, "androidx.constraintlayout", "constraintlayout"),
                version = Version.fromString("1.1.3"),
                dependencyType = DependencyType.MAIN
            )
        }

    override fun createStdlibType(configurationData: ModuleConfigurationData, module: Module): StdlibType? =
        StdlibType.StdlibJdk7


    private object FileTemplateDescriptors {
        val activityMainXml = FileTemplateDescriptor(
            "android/activity_main.xml.vm",
            "src" / "main" / "res" / "layout" / "activity_main.xml"
        )

        val androidManifestXml = FileTemplateDescriptor(
            "android/AndroidManifest.xml.vm",
            "src" / "main" / "AndroidManifest.xml"
        )

        fun mainActivityKt(javaPackage: JavaPackage) = FileTemplateDescriptor(
            "android/MainActivity.kt.vm",
            "src" / "main" / "java" / javaPackage.asPath() / "MainActivity.kt"
        )
    }
}