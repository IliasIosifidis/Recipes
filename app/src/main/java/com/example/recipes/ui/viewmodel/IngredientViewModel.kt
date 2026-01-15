package com.example.recipes.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.remote.Ingredients
import com.example.recipes.data.remote.MealApiFactory
import kotlinx.coroutines.launch

class IngredientViewModel : ViewModel(){

    var ingredients by mutableStateOf<List<Ingredients>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun getIngredients(){
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val res = MealApiFactory.api.getIngredients()
                ingredients = res.meals
            } catch (e: Exception){
                errorMessage = e.message
                ingredients = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    fun clearIngredients(){
        ingredients = emptyList()
    }
}