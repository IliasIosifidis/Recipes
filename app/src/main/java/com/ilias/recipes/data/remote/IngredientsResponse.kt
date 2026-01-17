package com.ilias.recipes.data.remote

data class IngredientsResponse(
    val meals: List<Ingredients>
)

data class Ingredients(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String?,
    val strType: String?
)

