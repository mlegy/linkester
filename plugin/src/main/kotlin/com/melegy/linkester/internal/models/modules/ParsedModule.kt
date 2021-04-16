package com.melegy.linkester.internal.models.modules

data class ParsedModule(
    val name: String,
    val manifestList: List<ParsedManifest>
)
