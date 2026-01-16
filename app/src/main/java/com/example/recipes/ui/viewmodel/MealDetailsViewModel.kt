package com.example.recipes.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.remote.Meal
import com.example.recipes.data.remote.MealApi
import com.example.recipes.data.remote.MealApiFactory
import kotlinx.coroutines.launch

class MealDetailsViewModel : ViewModel(){
    var isLoading by mutableStateOf(false)
        private set

    var meal by mutableStateOf<Meal?>(null)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun loadFood(id: String){
        isLoading = true
        error = null

        viewModelScope.launch {
            try {
                var res = MealApiFactory.api.getMealDetails(id)
                meal = res.meals?.firstOrNull()
            } catch (e: Exception){
                error = e.message ?: "Unknown error"
            } finally {
                isLoading = false
            }
        }
    }
}