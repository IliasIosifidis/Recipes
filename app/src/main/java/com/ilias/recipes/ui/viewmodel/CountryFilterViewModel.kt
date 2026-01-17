package com.ilias.recipes.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilias.recipes.data.remote.MealApiFactory
import com.ilias.recipes.data.remote.MealPreview
import kotlinx.coroutines.launch

class CountryFilterViewModel : ViewModel() {
    var countryResults by mutableStateOf<List<MealPreview>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun filterCountry(county: String){
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val res = MealApiFactory.api.filterByCountry(county)
                countryResults = res.meals.orEmpty()
            } catch (e: Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}