package com.example.recipes.data

import android.accessibilityservice.GestureDescription
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class CategoryResponse(
    val categories: List<Category> = emptyList()
)

data class Category(
    val idCategory: String? = null,
    val strCategory: String? = null,
    val strCategoryThumb: String? = null,
    val strCategoryDescription: String? = null
)