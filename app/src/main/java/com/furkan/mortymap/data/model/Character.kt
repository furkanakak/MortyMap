package com.furkan.mortymap.data.model

import com.furkan.mortymap.common.anotation.SelectableId

data class Character(
    @SelectableId
    val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Location,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)