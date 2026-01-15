package com.example.recipes.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.remote.Meal
import com.example.recipes.data.remote.MealApiFactory
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel(){

    var query by mutableStateOf("chicken")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var meals by mutableStateOf<List<Meal>>(emptyList())
        private set

    fun search(){
        val q = query.trim()
        if (q.isEmpty()){
            errorMessage = "Type something to search"
            meals = emptyList()
            return
        }

        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val res = MealApiFactory.api.searchMeals(q)
                meals = res.meals ?: emptyList()
            } catch (e: Exception){
                errorMessage = e.message ?: "Something went wrong"
                meals = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    fun onQueryChange(newValue: String){
        query = newValue
    }

    fun cleanResults(){
        meals = emptyList()
        errorMessage = null
    }
}