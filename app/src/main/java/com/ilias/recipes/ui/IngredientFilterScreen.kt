package com.ilias.recipes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ilias.recipes.navigation.Routes
import com.ilias.recipes.ui.viewmodel.IngredientFilterViewModel

@Composable
fun IngredientFilterScreen(
    ingredient: String,
    navController: NavHostController,
    vm: IngredientFilterViewModel = viewModel()
) {
    LaunchedEffect(ingredient) {
        vm.filterIngredient(ingredient)
    }

    Image(
        painter = painterResource(com.ilias.recipes.R.drawable.ingredients),
        contentDescription = null,
        contentScale = ContentScale.FillHeight
    )
    LazyColumn {
        items(vm.ingredientFilterResult){ ingredient ->
            IngredientDetailsRow(
                title = ingredient.strMeal,
                onClick = {
                    navController.navigate(Routes.mealDetails(ingredient.idMeal))
                }
            )
        }
    }
}

@Composable
private fun IngredientDetailsRow(title: String, onClick: () -> Unit) {
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