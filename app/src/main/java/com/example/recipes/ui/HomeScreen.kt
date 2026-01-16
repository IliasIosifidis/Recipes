package com.example.recipes.ui

import androidx.compose.material3.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipes.R
import com.example.recipes.ui.viewmodel.RecipesViewModel

@Composable
fun HomeScreen(
    onShowCategories: () -> Unit,
    onShowIngredients: () -> Unit,
    onShowCountries: () -> Unit,
    onShowSearch: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        var query by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        Image(
            painter = painterResource(R.drawable.home_screen_big),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xD7000000),
                    containerColor = Color(0xB5FF8922)
                ),
                onClick = onShowCategories
            ) {
                Text(
                    text = "FOOD CATEGORIES",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }

            Button(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xD7000000),
                    containerColor = Color(0xB598FF22)
                ),
                onClick = onShowIngredients
            ) {
                Text(
                    text = "INGREDIENTS",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }

            Button(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(.95f)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xD7000000),
                    containerColor = Color(0xB522FFCB)
                ),
                onClick = onShowCountries
            ) {
                Text(
                    text = "COUNTRIES",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(.95f)
            ) {
                TextField(
                    textStyle = TextStyle(
                        fontSize = 45.sp,
                        lineBreak = LineBreak.Simple
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(.8f)
                        .height(100.dp),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color(0xFF9C78DA),
                        unfocusedContainerColor = Color(0xFF673AB7),
                        focusedContainerColor = Color(0xFF1A102F)
                    ),
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Food name 🔎", fontSize = 35.sp) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (query.isNotEmpty()) {
                                onShowSearch(query)
                                focusManager.clearFocus()
                            }
                        }
                    )
                )
                Spacer(Modifier.width(8.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFF1A102F)),
                    modifier = Modifier
                        .height(100.dp)
                        .weight(.2f),
                    onClick = { onShowSearch(query) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = Color.White,
                        contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                }
            }
        }
    }
}