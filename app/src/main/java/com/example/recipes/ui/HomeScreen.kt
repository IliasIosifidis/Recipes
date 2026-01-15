package com.example.recipes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipes.R

@Composable
fun HomeScreen(
    onShowCategories: () -> Unit,
    onShowIngredients: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.home_screen_big),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ElevatedButton(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xD7000000),
                    containerColor = Color(0xB5FF8922)
                ),
                onClick = onShowCategories
            ) { Text(
                text = "FOOD CATEGORIES",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic)
            }

            ElevatedButton(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xD7000000),
                    containerColor = Color(0xB598FF22)
                ),
                onClick = onShowIngredients
            ) { Text(
                text = "INGREDIENTS",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic)
            }

            ElevatedButton(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xD7000000),
                    containerColor = Color(0xB522FFCB)
                ),
                onClick = onShowCategories
            ) { Text(
                text = "COUNTRIES",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic)
            }
        }
    }
}