package com.example.recipes.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealsResponse(
    @SerialName("meals") val meals: List<Meal>? = null
)

data class Meal(
    @SerialName("idMeal") val idMeal: String? = null,
    @SerialName("strMeal") val strMeal: String? = null,
    @SerialName("strMealThumb") val strMealThumb: String? = null
)
