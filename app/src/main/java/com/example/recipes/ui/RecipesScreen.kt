package com.example.recipes.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipesScreen(padding: PaddingValues){
    Text(
        text = "Recipes Screen",
        modifier = Modifier
            .padding(padding)
            .padding(15.dp)
    )
}