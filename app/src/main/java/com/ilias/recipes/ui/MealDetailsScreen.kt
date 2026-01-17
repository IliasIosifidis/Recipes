package com.ilias.recipes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ilias.recipes.R
import com.ilias.recipes.data.remote.ingredientsWithMeasures
import com.ilias.recipes.ui.viewmodel.MealDetailsViewModel

@Composable
fun MealDetailsScreen(
    mealId: String,
    vm: MealDetailsViewModel = viewModel()
){
    LaunchedEffect(mealId) { vm.loadFood(mealId) }
    val scrollState = rememberScrollState()

    when {
        vm.isLoading -> Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Loading categories")
            CircularProgressIndicator()
        }

        vm.error != null -> Text("Error ${vm.error}")
        vm.meal == null -> Text("No details for this meal")
        else -> {
            val m = vm.meal!!
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = vm.meal?.strMealThumb)
                    .crossfade(true)
                    .build(),
                contentDescription = vm.meal?.strMeal,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.home_screen)
                )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(.99f)
                    .background(
                        color = Color(0xBE000000),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(10.dp)
                    .verticalScroll(scrollState)
            ){
                Text(text = m.strMeal ?: "",style = MaterialTheme.typography.titleLarge)
                HorizontalDivider(color = Color(0xE4FFFFFF))
                Text("Ingredients: ",style = MaterialTheme.typography.titleLarge)
                vm.meal?.ingredientsWithMeasures()?.forEach { line ->
                    Text(
                        text = "• ${line.measure} ${line.ingredient}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
                HorizontalDivider(color = Color(0xE4FFFFFF))
                Text("Instructions: ",style = MaterialTheme.typography.titleLarge)
                Text(text = "Instructions ${m.strInstructions}")
                HorizontalDivider(color = Color(0xE4FFFFFF))
                Text(text = "Country of origin ${vm.meal?.strArea}")
            }
        }
    }
}