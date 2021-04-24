package com.melegy.linkester.internal

import com.google.gson.Gson
import com.melegy.linkester.internal.managers.DeepLinksCollector
import com.melegy.linkester.internal.managers.ManifestParser
import com.melegy.linkester.internal.models.modules.RawModule

object DeepLinksCollectorProcessor {
    operator fun invoke(rawModules: List<RawModule>): String {
        val parsedModules = ManifestParser(rawModules)
        val deepLinks = DeepLinksCollector(parsedModules)
        return  Gson().toJson(deepLinks)
    }
}
