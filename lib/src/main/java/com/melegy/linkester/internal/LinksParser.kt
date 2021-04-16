package com.melegy.linkester.internal

import android.content.Context
import org.json.JSONArray

internal object LinksParser {
    internal operator fun invoke(context: Context): Links {
        val autoCollectedLinks =
            parseResourceFile(context, "linkester_auto_collected_links")
        val manuallyAddedLinks =
            parseResourceFile(context, "linkester_manually_added_links")

        return Links(autoCollectedLinks, manuallyAddedLinks)
    }

    private fun parseResourceFile(context: Context, fileName: String): List<Link> {
        val resId = context.resources.getIdentifier(fileName, "raw", context.packageName)

        val rawString = context.resources.openRawResource(resId)
            .bufferedReader().use { it.readText() }

        val rawArray = JSONArray(rawString)
        val parsedLinks = mutableListOf<Link>()

        for (i in 0 until rawArray.length()) {
            val title = rawArray.getJSONObject(i).getString("title")
            val link = rawArray.getJSONObject(i).getString("link")
            parsedLinks.add(Link(title, link))
        }
        return parsedLinks
    }
}
