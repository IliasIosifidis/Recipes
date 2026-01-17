package com.ilias.recipes.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilias.recipes.data.remote.Area
import com.ilias.recipes.data.remote.MealApiFactory
import kotlinx.coroutines.launch
import java.lang.Exception

class AreaViewModel: ViewModel() {

    var areas by mutableStateOf<List<Area>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun getAreas(){
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val res = MealApiFactory.api.getArea()
                areas = res.meals ?: emptyList()
            } catch (e: Exception) {
                errorMessage = e.message
                areas = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    fun clearArea(){
        areas = emptyList()
    }
}