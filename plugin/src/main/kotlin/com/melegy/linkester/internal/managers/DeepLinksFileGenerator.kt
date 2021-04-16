package com.melegy.linkester.internal.managers

import com.google.gson.Gson
import com.melegy.linkester.internal.models.DeepLink
import java.io.File
import java.nio.file.Files

internal object DeepLinksFileGenerator {

    operator fun invoke(targetDirectory: File, filesDeepLinksRaw: List<DeepLink>) {
        val gson = Gson().toJson(filesDeepLinksRaw)

        var outputDirectory = targetDirectory.toPath()
        outputDirectory = outputDirectory.resolve("raw")
        Files.createDirectories(outputDirectory)

        val outputPath = outputDirectory.resolve("linkester_auto_collected_links.json")
        outputPath.toFile().writeText(gson)
    }
}
