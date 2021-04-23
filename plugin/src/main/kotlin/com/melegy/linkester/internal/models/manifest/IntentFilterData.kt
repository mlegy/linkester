package com.melegy.linkester.internal.models.manifest

import com.melegy.linkester.internal.constants.XMLParsingConstants.ANDROID_NAMESPACE
import com.melegy.linkester.internal.constants.XMLParsingConstants.DATA_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.HOST_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.PATH_PATTERN_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.PATH_PREFIX_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.PATH_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.SCHEME_TAG
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = DATA_TAG)
@XmlAccessorType(XmlAccessType.FIELD)
data class IntentFilterData(
    @field:XmlAttribute(name = SCHEME_TAG, namespace = ANDROID_NAMESPACE)
    val scheme: String,

    @field:XmlAttribute(name = HOST_TAG, namespace = ANDROID_NAMESPACE)
    val host: String,

    @field:XmlAttribute(name = PATH_TAG, namespace = ANDROID_NAMESPACE)
    val path: String,

    @field:XmlAttribute(name = PATH_PREFIX_TAG, namespace = ANDROID_NAMESPACE)
    val pathPrefix: String,

    @field:XmlAttribute(name = PATH_PATTERN_TAG, namespace = ANDROID_NAMESPACE)
    val pathPattern: String,
) {
    @Suppress("unused")
    constructor() : this(scheme = "", host = "", path = "", pathPrefix = "", pathPattern = "")
}
