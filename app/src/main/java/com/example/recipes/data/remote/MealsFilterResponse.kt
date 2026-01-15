package com.example.recipes.data.remote

data class MealsFilterResponse(
    val meals: List<MealPreview>?
)

data class MealPreview(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)