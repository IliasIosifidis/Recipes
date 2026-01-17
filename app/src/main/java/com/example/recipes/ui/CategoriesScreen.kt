package com.example.recipes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.recipes.Routes
import com.example.recipes.ui.viewmodel.CategoryViewModel

@Composable
fun CategoriesScreen(
    modifier: Modifier,
    padding: PaddingValues,
    vm: CategoryViewModel = viewModel(),
    navController: NavHostController
) {
    if (vm.categories.isEmpty() && !vm.isLoading) {
        vm.getCategories()
    }
    when {
        vm.isLoading -> {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Loading categories")
                CircularProgressIndicator()
            }
        }

        vm.errorMessage != null -> Text(
            text = vm.errorMessage!!,
            color = MaterialTheme.colorScheme.error
        )

        else -> {
            Image(
                painter = painterResource(com.example.recipes.R.drawable.food_categories),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(vm.categories) { category ->
                    CategoryRow(
                        title = category.strCategory ?: "Unknown category",
                        onClick = {
                            navController.navigate(Routes.category(category.strCategory.toString()))
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryRow(title: String, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xCEBB8F6B),
            contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable(onClick = { onClick() }),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 3f,
                        offset = Offset(3.0f, 3.0f)
                    ),
                ),
                fontSize = (25.sp)
            )
        }
    }
}