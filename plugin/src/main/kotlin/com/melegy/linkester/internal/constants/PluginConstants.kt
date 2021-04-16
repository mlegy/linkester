package com.melegy.linkester.internal.constants

import java.io.File

internal object PluginConstants {
    const val PLUGIN_NAME = "Linkester"
    val TARGET_DIRECTORY = "generated${File.separator}" +
            "source${File.separator}" +
            "${PLUGIN_NAME.toLowerCase()}${File.separator}" +
            "com${File.separator}" +
            "melegy${File.separator}" +
            "res"
}
