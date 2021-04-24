package com.melegy.linkester

import com.google.common.base.Charsets
import com.google.common.io.Files
import com.melegy.linkester.internal.DeepLinksCollectorProcessor
import com.melegy.linkester.internal.models.modules.RawModule
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
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
    lateinit var intermediateDir: File

    init {
        group = "Linkester"
        description = "Collect all deep links in the App to be used in the Linkester library"
    }

    @TaskAction
    fun collectAllDeepLinks() {
        val links = DeepLinksCollectorProcessor(rawModules)
        val rawDirectory = File(intermediateDir, "raw")
        if (!rawDirectory.exists() && !rawDirectory.mkdirs()) {
            throw GradleException("Failed to create folder: $rawDirectory")
        }

        val jsonFile = File(rawDirectory, "linkester_auto_collected_links.json")
        Files.asCharSink(jsonFile, Charsets.UTF_8).write(links)
    }

    private fun retrieveRawModules() = project.rootProject.allprojects.map { it.toRawModule() }

    private fun Project.toRawModule() = RawModule(name, manifestFiles)

    private val Project.manifestFiles
        get() = projectDir
            .walk()
            .maxDepth(3)
            .filter { it.name == "AndroidManifest.xml" }
            .toList()
}
