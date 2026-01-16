package com.example.recipes.data.remote

data class CategoryFilterResponse(val meals: List<CategoryMealPreview>?)

data class CategoryMealPreview(
    val idMeal: String?,
    val strMeal: String?,
    val strMealThumb: String?
)