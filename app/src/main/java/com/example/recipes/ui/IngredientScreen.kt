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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipes.ui.viewmodel.IngredientViewModel

@Composable
fun IngredientScreen(
    modifier: Modifier,
    padding: PaddingValues,
    vm: IngredientViewModel = viewModel()
) {
    if (vm.ingredients.isEmpty() && !vm.isLoading) {
        vm.getIngredients()
    }
    when {
        vm.isLoading ->
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Loading categories")
                CircularProgressIndicator()
            }

        vm.errorMessage != null -> Text(
            text = "Error ${vm.errorMessage}",
            color = MaterialTheme.colorScheme.error
        )

        else -> {
            Image(
                painter = painterResource(com.example.recipes.R.drawable.ingredients),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(vm.ingredients) { ingredient ->
                    IngredientsRow(title = ingredient.strIngredient)
                }
            }
        }
    }
}

@Composable
fun IngredientsRow(title: String) {
    Card(
        colors = CardDefaults.cardColors(
        containerColor = Color(0xCE49B93D),
        contentColor = Color.Black
    ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = {}),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 3f,
                        offset = Offset(3.0f, 3.0f)
                    ),
                )
            )
        }
    }
}