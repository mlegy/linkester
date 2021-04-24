package com.melegy.linkester.internal.managers

import com.melegy.linkester.internal.models.DeepLink
import com.melegy.linkester.internal.models.manifest.Activity
import com.melegy.linkester.internal.models.manifest.IntentFilterData
import com.melegy.linkester.internal.models.modules.ParsedModule

internal object DeepLinksCollector {

    operator fun invoke(parsedModules: List<ParsedModule>) =
        parsedModules.flatMap { it.getDeepLinksOrNull() }

    private fun ParsedModule.getDeepLinksOrNull() = manifestList.flatMap { manifest ->
        manifest.application.activityList.flatMap { activity -> activity.deepLinks }
    }

    private val Activity.deepLinks
        get() = intentFilter
            .filter { it.data.scheme.isNotBlank() }
            .map { DeepLink(className.activityName, it.data.deepLink) }

    private val String.activityName
        get() = split('.').last()

    private val IntentFilterData.deepLink: String
        get() = when {
            path.isNotBlank() -> "$scheme://$host$path"
            pathPrefix.isNotBlank() -> "$scheme://$host$pathPrefix"
            else -> "$scheme://$host"
        }
}
