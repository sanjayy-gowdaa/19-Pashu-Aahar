package com.example.pashuaahar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Tip(val title: String, val description: String)

@Composable
fun TipsScreen() {
    val tips = listOf(
        Tip("Regular Deworming", "Deworm your cattle every 3-6 months to maintain health and milk yield."),
        Tip("Clean Drinking Water", "Ensure 24/7 access to fresh, clean water for better digestion."),
        Tip("Fodder Storage", "Keep dry fodder in a moisture-free area to prevent fungal growth."),
        Tip("Mineral Mixture", "Always add 50g of mineral mixture to the daily concentrate mix."),
        Tip("Calf Care", "Provide colostrum within 1 hour of birth for strong immunity.")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Veterinary Tips", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tips) { tip ->
                TipCard(tip)
            }
        }
    }
}

@Composable
fun TipCard(tip: Tip) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(tip.title, style = MaterialTheme.typography.titleMedium)
                Text(tip.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
