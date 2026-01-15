package com.example.recipes.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AreaResponse(
    val area: List<Area>?
)

@Serializable
data class Area(
    val strArea: String
)
