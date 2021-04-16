package com.melegy.linkester.internal.models.manifest

import com.melegy.linkester.internal.constants.XMLParsingConstants.ACTIVITY_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.ANDROID_NAMESPACE
import com.melegy.linkester.internal.constants.XMLParsingConstants.INTENT_FILTER_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.NAME_TAG
import javax.xml.bind.annotation.XmlAccessType.FIELD
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = ACTIVITY_TAG)
@XmlAccessorType(FIELD)
data class Activity(
    @field:XmlAttribute(name = NAME_TAG, namespace = ANDROID_NAMESPACE)
    val className: String,

    @field:XmlElement(name = INTENT_FILTER_TAG)
    val intentFilter: MutableList<IntentFilter>,
) {
    @Suppress("unused")
    constructor() : this(className = "", intentFilter = mutableListOf())
}
