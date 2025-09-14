package com.furkan.mortymap.data.model

import com.furkan.mortymap.common.anotation.SelectableId

data class Origin(
    @SelectableId
    val id: Int,
    val created: String,
    val dimension: String,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)