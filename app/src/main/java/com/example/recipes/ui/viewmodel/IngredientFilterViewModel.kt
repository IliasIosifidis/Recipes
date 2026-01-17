package com.example.recipes.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.remote.MealApiFactory
import com.example.recipes.data.remote.MealPreview
import kotlinx.coroutines.launch

class IngredientFilterViewModel : ViewModel () {

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var ingredientFilterResult by mutableStateOf<List<MealPreview>>(emptyList())
        private set

    fun filterIngredient(ingredient: String){
        errorMessage = null
        isLoading = false

        viewModelScope.launch {
            try {
                val res = MealApiFactory.api.filterByIngredients(ingredient = ingredient)
                ingredientFilterResult = res.meals.orEmpty()
            } catch (e: Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}