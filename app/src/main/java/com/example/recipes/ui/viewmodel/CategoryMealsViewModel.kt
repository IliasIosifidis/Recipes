package com.example.recipes.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.remote.MealApiFactory
import com.example.recipes.data.remote.MealPreview
import kotlinx.coroutines.launch

class CategoryMealsViewModel : ViewModel() {

    var mealsByCategory by mutableStateOf<List<MealPreview>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun filterCategory(category: String){
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val res = MealApiFactory.api.filterByCategory(category)
                mealsByCategory = res.meals ?: emptyList()
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}