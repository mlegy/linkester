package com.melegy.linkester.internal.models.modules

import java.io.File

data class RawModule(
    val name: String,
    val manifestFiles: List<File>
)
