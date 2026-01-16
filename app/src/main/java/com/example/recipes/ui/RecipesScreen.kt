package com.example.recipes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipes.R
import com.example.recipes.ui.viewmodel.RecipesViewModel

@Composable
fun RecipesScreen(
    modifier: Modifier,
    padding: PaddingValues,
    query: String,
    vm: RecipesViewModel = viewModel()
) {
    LaunchedEffect(query) {
        vm.search(query)
    }
    Image(
        painter = painterResource(com.example.recipes.R.drawable.spoon),
        contentDescription = null,
        contentScale = ContentScale.FillHeight
    )
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(vm.meals) { meal ->
            MealRow(title = meal.strMeal ?: "(no name)")
        }
    }
}

@Composable
private fun MealRow(title: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xCEE8DDDD),
            contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable(onClick = {}),
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
            ),
            fontSize = (25.sp)
        )
    }
}


















