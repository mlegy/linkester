package com.melegy.linkester.internal.models.manifest

import com.melegy.linkester.internal.constants.XMLParsingConstants.ACTIVITY_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.APPLICATION_TAG
import javax.xml.bind.annotation.XmlAccessType.FIELD
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = APPLICATION_TAG)
@XmlAccessorType(FIELD)
data class Application(
    @field:XmlElement(name = ACTIVITY_TAG)
    val activityList: MutableList<Activity>
) {
    @Suppress("unused")
    constructor() : this(activityList = mutableListOf())
}
