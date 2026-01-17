package com.ilias.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ilias.recipes.navigation.Routes
import com.ilias.recipes.ui.AreaScreen
import com.ilias.recipes.ui.CategoriesScreen
import com.ilias.recipes.ui.CategoryFilterScreen
import com.ilias.recipes.ui.CountryFilterScreen
import com.ilias.recipes.ui.HomeScreen
import com.ilias.recipes.ui.IngredientFilterScreen
import com.ilias.recipes.ui.IngredientScreen
import com.ilias.recipes.ui.MealDetailsScreen
import com.ilias.recipes.ui.RecipesScreen
import com.ilias.recipes.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesTheme {
                val navController = rememberNavController()
                Scaffold(

                    topBar = {
                        TopAppBar(
                            modifier = Modifier.fillMaxWidth(),
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(color = 0xFF5B2110),
                                titleContentColor = Color(color = 0xFFFDE2D0),

                            ),
                            title = {
                                Text(
                                    text = "Recipes App",
                                    textAlign = TextAlign.Center,
                                    fontSize = 30.sp,
                                )
                            },
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.background,
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HOME,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.HOME) {
                            HomeScreen(
                                onShowCategories = { navController.navigate(Routes.CATEGORIES) },
                                onShowIngredients = { navController.navigate(Routes.INGREDIENTS) },
                                onShowCountries = { navController.navigate(Routes.COUNTRIES) },
                                onShowSearch = { q ->
                                    navController.navigate(Routes.search(q))
                                }
                            )
                        }
                        composable(Routes.CATEGORIES) {
                            CategoriesScreen(
                                modifier = Modifier, padding = PaddingValues(),navController = navController)
                        }
                        composable(Routes.INGREDIENTS) {
                            IngredientScreen(modifier = Modifier, padding = PaddingValues(), navController = navController)
                        }
                        composable(Routes.COUNTRIES) {
                            AreaScreen(modifier = Modifier, padding = PaddingValues(), navController = navController)
                        }
                        // SEARCH FUNCTION
                        composable(
                            route = Routes.SEARCH,
                            arguments = listOf(navArgument("query") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val query = backStackEntry.arguments?.getString("query").orEmpty()
                            RecipesScreen(
                                modifier = Modifier,
                                padding = PaddingValues(),
                                query = query,
                                onMealClick = { id -> navController.navigate(Routes.mealDetails(id)) }
                            )
                        }
                        // MEAL DETAILS SCREEN
                        composable(
                            route = Routes.MEAL_DETAILS,
                            arguments = listOf(navArgument("id") { type = NavType.StringType })
                        ) { backStackEntry ->
                                val id = backStackEntry.arguments?.getString(("id").orEmpty())
                                MealDetailsScreen(mealId = id!!)
                        }
                        // MEALS AFTER THE CATEGORY CLICK
                        composable(
                            route = Routes.CATEGORY,
                            arguments = listOf(navArgument("name") {type = NavType.StringType})
                        ) { backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name").orEmpty()
                            CategoryFilterScreen(category = name, navController = navController)
                        }
                        // MEALS AFTER A COUNTRY CLICK
                        composable(
                            route = Routes.COUNTRY,
                            arguments = listOf(navArgument("country") { type = NavType.StringType }),
                        ){backStackEntry ->
                            val country = backStackEntry.arguments?.getString("country").orEmpty()
                            CountryFilterScreen(country = country, navController = navController)
                        }
                        // MEALS AFTER THE INGREDIENT CLICK
                        composable(
                            route = Routes.INGREDIENT,
                            arguments = listOf(navArgument("ingredient") {type = NavType.StringType})
                        ) {backStackEntry ->
                            val ingredient = backStackEntry.arguments?.getString("ingredient").orEmpty()
                            IngredientFilterScreen(ingredient = ingredient, navController = navController)

                        }
                    }
                }
            }
        }
    }
}