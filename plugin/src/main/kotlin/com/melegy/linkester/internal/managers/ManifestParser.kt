package com.melegy.linkester.internal.managers

import com.melegy.linkester.internal.models.manifest.Manifest
import com.melegy.linkester.internal.models.modules.ParsedManifest
import com.melegy.linkester.internal.models.modules.ParsedModule
import com.melegy.linkester.internal.models.modules.RawModule
import java.io.File
import javax.xml.bind.JAXBContext

internal object ManifestParser {

    private val jaxbUnMarshaller =
        JAXBContext.newInstance(Manifest::class.java).createUnmarshaller()

    operator fun invoke(rawModules: List<RawModule>) = rawModules.map { it.parse() }

    private fun RawModule.parse() = ParsedModule(name, manifestFiles.toManifestList())

    private fun List<File>.toManifestList() = map { it.toManifest() }

    private fun File.toManifest() = jaxbUnMarshaller
        .unmarshal(this)
        .let { it as Manifest }
        .let { ParsedManifest(absolutePath, it.application) }
}
