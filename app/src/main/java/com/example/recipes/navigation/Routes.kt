package com.example.recipes.navigation

import android.net.Uri

object Routes {
    const val HOME = "home"
    const val CATEGORIES = "categories"
    const val INGREDIENTS = "ingredients"
    const val COUNTRIES = "countries"
    const val MEAL_DETAILS = "meal/{id}"
    fun mealDetails(id: String) = "meal/${Uri.encode(id)}"

    // SEARCH CLICKABLE
    const val SEARCH = "search/{query}"
    fun search(query: String) = "search/${Uri.encode(query)}"

    // CATEGORY CLICKABLE
    const val CATEGORY = "category/{name}"
    fun category(name: String) = "category/${Uri.encode(name)}"

    // COUNTRY CLICKABLE
    const val COUNTRY = "country/{country}"
    fun countrySearch(country: String) = "country/${Uri.encode(country)}"

    //INGREDIENTS CLICKABLE
    const val INGREDIENT = "ingredient/{ingredient}"
    fun ingredientSearch(ingredient: String) = "ingredient/${Uri.encode(ingredient)}"
}