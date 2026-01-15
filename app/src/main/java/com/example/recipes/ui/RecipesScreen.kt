package com.example.recipes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipes.ui.viewmodel.RecipesViewModel

@Composable
fun RecipesScreen(
    modifier: Modifier,
    padding: PaddingValues,
    vm: RecipesViewModel = viewModel()
){
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(15.dp)
    ) {
        Text(
            text = "Recipes",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = vm.query,
            onValueChange = vm::onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text(text = "Search recipes")}
        )
        // SEARCH BUTTON
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { vm.search() },
                modifier = Modifier.fillMaxWidth().weight(1f),
                enabled = !vm.isLoading
            ) {
                Text(if (vm.isLoading) "Searching..." else "Search")
            }
            Spacer(Modifier.width(5.dp))
            // CLEAR SEARCH LIST BUTTON
            if (!vm.meals.isEmpty()){
                Button(
                    onClick = { vm.cleanResults() },
                    modifier = Modifier.weight(.2f),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text("✕")
                }
            }


            vm.errorMessage?.let{ msg ->
                Spacer(Modifier.height(10.dp))
                Text(text = msg, color = MaterialTheme.colorScheme.error)
            }
        }


        Spacer(Modifier.height(10.dp))

        // LIST OF RECIPES START
        if (vm.meals.isEmpty() && !vm.isLoading && vm.errorMessage == null){
            Text("No results yet. Try search for: chicken, beef, pasta etc")
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(vm.meals){ meal ->
                    MealRow(title = meal.strMeal ?: "(no name)")
                }
            }
        }

    }
}

@Composable
private fun MealRow(title: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}


















