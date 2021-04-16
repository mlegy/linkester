package com.melegy.linkester.internal

import com.melegy.linkester.internal.managers.DeepLinksFileGenerator
import com.melegy.linkester.internal.managers.DeepLinksCollector
import com.melegy.linkester.internal.managers.ManifestParser
import com.melegy.linkester.internal.models.modules.RawModule
import java.io.File

internal class DeepLinksCollectorProcessor(private val targetDirectory: File) {
    fun process(rawModules: List<RawModule>) {
        val parsedModules = ManifestParser(rawModules)
        val moduleWithDeepLinks = DeepLinksCollector(parsedModules)
        DeepLinksFileGenerator(targetDirectory, moduleWithDeepLinks)
    }
}
