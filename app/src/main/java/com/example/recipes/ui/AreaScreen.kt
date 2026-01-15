package com.example.recipes.ui

import android.widget.Button
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipes.ui.viewmodel.AreaViewModel

@Composable
fun AreaScreen(
    modifier: Modifier,
    padding: PaddingValues,
    vm: AreaViewModel = viewModel()
) {
    var showArea by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier.padding(16.dp),
        onClick = {
            showArea = !showArea
            if (showArea && vm.areas!!.isEmpty() && vm.isLoading){
                vm.getAreas()
            } else {
                vm.clearArea()
            }
        }) {
        Text(text = if (showArea) "Hide Areas" else "Show Areas")
    }
}