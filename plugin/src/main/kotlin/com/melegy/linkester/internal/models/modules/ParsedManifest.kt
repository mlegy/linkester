package com.melegy.linkester.internal.models.modules

import com.melegy.linkester.internal.models.manifest.Application

data class ParsedManifest(
    val path: String,
    val application: Application
)
