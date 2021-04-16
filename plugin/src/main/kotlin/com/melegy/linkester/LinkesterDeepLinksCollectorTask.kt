package com.melegy.linkester

import com.melegy.linkester.internal.DeepLinksCollectorProcessor
import com.melegy.linkester.internal.constants.PluginConstants.PLUGIN_NAME
import com.melegy.linkester.internal.constants.PluginConstants.TARGET_DIRECTORY
import com.melegy.linkester.internal.models.modules.RawModule
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

internal open class LinkesterDeepLinksCollectorTask : DefaultTask() {

    private val rawModules by lazy { retrieveRawModules() }

    @InputFile
    val buildScript: File = project.buildFile

    @InputFiles
    val manifestFiles = rawModules.flatMap { it.manifestFiles }

    @OutputDirectory
    val targetDirectory = project.buildDir.resolve(TARGET_DIRECTORY)

    init {
        group = PLUGIN_NAME
        description = DESCRIPTION
    }

    @TaskAction
    fun collectAllDeepLinks() {
        DeepLinksCollectorProcessor(targetDirectory).run {
            process(rawModules)
        }
    }

    private fun retrieveRawModules() = project.rootProject.allprojects.map { it.toRawModule() }

    private fun Project.toRawModule() = RawModule(name, manifestFiles)

    private val Project.manifestFiles
        get() = projectDir
            .walk()
            .maxDepth(MANIFEST_FILE_DEPTH)
            .filter { it.name == MANIFEST_FILE_NAME }
            .toList()

    private companion object {
        private const val MANIFEST_FILE_DEPTH = 3
        private const val MANIFEST_FILE_NAME = "AndroidManifest.xml"
        private const val DESCRIPTION =
            "Collect all deep links in the App to be used in the Linkester library."
    }
}
