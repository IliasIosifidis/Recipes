package com.example.recipes

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipes.ui.AreaScreen
import com.example.recipes.ui.CategoriesScreen
import com.example.recipes.ui.HomeScreen
import com.example.recipes.ui.IngredientScreen
import com.example.recipes.ui.RecipesScreen
import com.example.recipes.ui.theme.RecipesTheme
import com.example.recipes.ui.viewmodel.RecipesViewModel

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
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            title = {
                                Text(
                                    text = "Home Screen",
                                    textAlign = TextAlign.Center
                                )
                            },
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.background
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
                            CategoriesScreen(modifier = Modifier, padding = PaddingValues())
                        }
                        composable(Routes.INGREDIENTS) {
                            IngredientScreen(modifier = Modifier, padding = PaddingValues())
                        }
                        composable(Routes.COUNTRIES) {
                            AreaScreen(modifier = Modifier, padding = PaddingValues())
                        }
                        composable(
                            route = Routes.SEARCH,
                            arguments = listOf(navArgument("query") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val query = backStackEntry.arguments?.getString("query").orEmpty()
                            RecipesScreen(
                                modifier = Modifier,
                                padding = PaddingValues(),
                                query = query
                            )
                        }
                    }
                }
            }
        }
    }
}

object Routes {
    const val HOME = "home"
    const val CATEGORIES = "categories"
    const val INGREDIENTS = "ingredients"
    const val COUNTRIES = "countries"

    const val SEARCH = "search/{query}"
    fun search(query: String) = "search/${Uri.encode(query)}"
}
























