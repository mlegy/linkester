package com.melegy.linkester.internal.models.manifest

import com.melegy.linkester.internal.constants.XMLParsingConstants.APPLICATION_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.MANIFEST_TAG
import javax.xml.bind.annotation.XmlAccessType.FIELD
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = MANIFEST_TAG)
@XmlAccessorType(FIELD)
data class Manifest(
    @field:XmlElement(name = APPLICATION_TAG)
    val application: Application
) {
    @Suppress("unused")
    constructor() : this(application = Application())
}
