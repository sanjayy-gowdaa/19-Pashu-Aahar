package com.example.pashuaahar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pashuaahar.logic.MainViewModel
import java.util.Locale

@Composable
fun RecipeScreen(viewModel: MainViewModel) {
    val recipe by viewModel.recipe.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Suggested Feed Recipe", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Total Concentrate: ${String.format(Locale.getDefault(), "%.2f", recipe.totalConcentrateKg)} kg / day",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recipe.ingredients) { ingredient ->
                IngredientCard(ingredient.name, ingredient.ratio)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Daily Savings Tip", fontWeight = FontWeight.Bold)
                val savings = recipe.estimatedCostMarket - recipe.estimatedCostHomeMade
                Text("Buying grains locally and mixing at home saves approx. ₹${String.format(Locale.getDefault(), "%.2f", savings)} per day!")
            }
        }
    }
}

@Composable
fun IngredientCard(name: String, amountKg: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text("Balanced Mix", style = MaterialTheme.typography.bodySmall)
            }
            Text(
                "${String.format(Locale.getDefault(), "%.2f", amountKg)} kg",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
