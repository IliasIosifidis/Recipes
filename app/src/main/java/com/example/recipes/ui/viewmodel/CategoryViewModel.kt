package com.example.recipes.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.Category
import com.example.recipes.data.MealApi
import com.example.recipes.data.MealApiFactory
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {

    var categories by mutableStateOf<List<Category>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun getCategories(){
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                var res = MealApiFactory.api.getCategories()
                categories = res.categories
            } catch (e: Exception){
                errorMessage = e.message
                categories = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    fun clearCategories(){
        categories = emptyList()
    }

}