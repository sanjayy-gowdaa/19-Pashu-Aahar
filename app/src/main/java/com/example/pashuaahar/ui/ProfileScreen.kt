package com.example.pashuaahar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pashuaahar.data.CowBreed
import com.example.pashuaahar.logic.MainViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: MainViewModel) {
    val profile by viewModel.cowProfile.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cow Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // Breed Selection
        Text("Select Breed", style = MaterialTheme.typography.titleMedium)
        Row(Modifier.padding(8.dp)) {
            CowBreed.entries.forEach { breed ->
                Row(
                    Modifier
                        .selectable(
                            selected = (profile.breed == breed),
                            onClick = { viewModel.updateProfile(profile.copy(breed = breed)) }
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (profile.breed == breed),
                        onClick = { viewModel.updateProfile(profile.copy(breed = breed)) }
                    )
                    Text(text = breed.displayName, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Weight Slider
        Text("Weight: ${profile.weightKg.toInt()} kg", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = profile.weightKg.toFloat(),
            onValueChange = { viewModel.updateProfile(profile.copy(weightKg = it.toDouble())) },
            valueRange = 200f..800f,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Milk Yield Slider
        Text("Current Milk Yield: ${profile.dailyMilkYieldLiters.toInt()} L", style = MaterialTheme.typography.titleMedium)
        Slider(
            value = profile.dailyMilkYieldLiters.toFloat(),
            onValueChange = { viewModel.updateProfile(profile.copy(dailyMilkYieldLiters = it.toDouble())) },
            valueRange = 0f..40f,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate("recipe") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generate Recipe")
        }
    }
}
