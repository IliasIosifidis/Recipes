package com.example.recipes.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealsResponse

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("list.php")
    suspend fun getArea(@Query("a") a: String = "list"): AreaResponse

    @GET("list.php")
    suspend fun getIngredients(@Query ("i") i : String = "list"): IngredientsResponse

    @GET("filter.php")
    suspend fun filterByCategory(@Query("c") category: String): MealsFilterResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): MealsResponse

    @GET("filter.php")
    suspend fun filterByCountry(@Query("a") country: String) : MealsFilterResponse
}

//www.themealdb.com/api/json/v1/1/list.php?i=list

object MealApiFactory {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: MealApi = retrofit.create(MealApi::class.java)
}

/*
V1 API
Code Icon API Methods using the developer test key '1' in the URL


Search meal by name
www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata

List all meals by first letter
www.themealdb.com/api/json/v1/1/search.php?f=a

Lookup full meal details by id
www.themealdb.com/api/json/v1/1/lookup.php?i=52772

Lookup a single random meal
www.themealdb.com/api/json/v1/1/random.php

Lookup a selection of 10 random meals
*Premium API Only
www.themealdb.com/api/json/v1/1/randomselection.php

List all meal categories
www.themealdb.com/api/json/v1/1/categories.php

Latest Meals
*Premium API Only
www.themealdb.com/api/json/v1/1/latest.php

List all Categories, Area, Ingredients
www.themealdb.com/api/json/v1/1/list.php?c=list
www.themealdb.com/api/json/v1/1/list.php?a=list
www.themealdb.com/api/json/v1/1/list.php?i=list

Filter by main ingredient
www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast

Filter by multi-ingredient
*Premium API Only
www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast,garlic,salt

Filter by Category
www.themealdb.com/api/json/v1/1/filter.php?c=Seafood

Filter by Area
www.themealdb.com/api/json/v1/1/filter.php?a=Canadian

Meal Thumbnail Images
Add /preview to the end of the meal image URL
/images/media/meals/llcbn01574260722.jpg/small
/images/media/meals/llcbn01574260722.jpg/medium
/images/media/meals/llcbn01574260722.jpg/large
Small meal thumbnail

Ingredient Thumbnail Images
*URL's match the ingredient name with an underscore for any spaces.

www.themealdb.com/images/ingredients/lime.png
www.themealdb.com/images/ingredients/lime-small.png
www.themealdb.com/images/ingredients/lime-medium.png
www.themealdb.com/images/ingredients/lime-large.png
Large Lime Thumbnail section seperator
 */