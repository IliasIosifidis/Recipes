package com.example.recipes.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class AreaResponse(
    val meals: List<Area>?
)

data class Area(
    val strArea: String
)
