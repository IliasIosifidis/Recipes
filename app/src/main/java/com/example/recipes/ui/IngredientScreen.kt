package com.example.recipes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipes.ui.viewmodel.IngredientViewModel

@Composable
fun IngredientScreen(
    modifier: Modifier,
    padding: PaddingValues,
    vm: IngredientViewModel = viewModel()
) {
    var showIngredients by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier.padding(16.dp),
        onClick = {
            showIngredients = !showIngredients
            if (showIngredients && vm.ingredients.isEmpty() && !vm.isLoading){
                vm.getIngredients()
            }
            else {
                vm.clearIngredients()
            }
        }
    ) {
        Text(if (showIngredients) "Hide Ingredients" else "Show Ingredients")
    }

    if (showIngredients){
        when{
            vm.isLoading -> Text("Loading Ingredients")
            vm.errorMessage !=null -> Text(
                text = "Error ${vm.errorMessage}",
                color = MaterialTheme.colorScheme.error)
            else -> LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(vm.ingredients){ingred ->
                    IngredientsRow(title = ingred.strIngredient)
                }
            }
        }
    }
}

@Composable
fun IngredientsRow(title: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = {}),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}