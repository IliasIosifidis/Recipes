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
import com.example.recipes.ui.viewmodel.AreaViewModel

@Composable
fun AreaScreen(
    modifier: Modifier,
    padding: PaddingValues,
    vm: AreaViewModel = viewModel()
) {

if (vm.areas.isEmpty() && !vm.isLoading){
    vm.getAreas()
}

when {
    vm.isLoading -> {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Areas are loading")
            CircularProgressIndicator()
        }
    }
    vm.errorMessage != null -> Text(
        text = "Error ${vm.errorMessage}",
        color = MaterialTheme.colorScheme.error
    )
    else ->{
            Image(
                painter = painterResource(com.example.recipes.R.drawable.countries),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items = vm.areas){ area ->
                    AreaRow(area.strArea ?: "Unknown Area")
                }
            }
        }
    }
}

@Composable
private fun AreaRow(title: String){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xCE3DB9A0),
            contentColor = Color.Black
        ),
        modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clickable(onClick = {}),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier,
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