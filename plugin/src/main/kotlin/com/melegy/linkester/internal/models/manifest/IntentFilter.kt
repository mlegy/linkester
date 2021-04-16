package com.melegy.linkester.internal.models.manifest

import com.melegy.linkester.internal.constants.XMLParsingConstants.DATA_TAG
import com.melegy.linkester.internal.constants.XMLParsingConstants.INTENT_FILTER_TAG
import javax.xml.bind.annotation.XmlAccessType.FIELD
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = INTENT_FILTER_TAG)
@XmlAccessorType(FIELD)
data class IntentFilter(
    @field:XmlElement(name = DATA_TAG)
    val data: IntentFilterData
) {
    @Suppress("unused")
    constructor() : this(data = IntentFilterData())
}
