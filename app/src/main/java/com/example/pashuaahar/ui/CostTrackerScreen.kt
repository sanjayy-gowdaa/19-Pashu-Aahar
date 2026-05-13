package com.example.pashuaahar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.pashuaahar.logic.MainViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.util.Locale

@Composable
fun CostTrackerScreen(viewModel: MainViewModel) {
    val recipe by viewModel.recipe.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Cost Comparison", style = MaterialTheme.typography.headlineMedium)
        Text("Estimated Daily Expenses", style = MaterialTheme.typography.bodyMedium)
        
        Spacer(modifier = Modifier.height(24.dp))

        // Chart
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            factory = { context ->
                BarChart(context).apply {
                    description.isEnabled = false
                    setFitBars(true)
                    xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Home-made", "Market Feed"))
                    xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
                    xAxis.setDrawGridLines(false)
                    axisLeft.setDrawGridLines(false)
                    axisRight.isEnabled = false
                }
            },
            update = { chart ->
                val entries = listOf(
                    BarEntry(0f, recipe.estimatedCostHomeMade.toFloat()),
                    BarEntry(1f, recipe.estimatedCostMarket.toFloat())
                )
                val dataSet = BarDataSet(entries, "Daily Cost (₹)")
                dataSet.colors = listOf(
                    android.graphics.Color.parseColor("#4CAF50"),
                    android.graphics.Color.parseColor("#F44336")
                )
                chart.data = BarData(dataSet)
                chart.invalidate()
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(Modifier.padding(16.dp)) {
                val monthlySavings = (recipe.estimatedCostMarket - recipe.estimatedCostHomeMade) * 30
                Text("Potential Monthly Savings", style = MaterialTheme.typography.titleMedium)
                Text(
                    "₹${String.format(Locale.getDefault(), "%.2f", monthlySavings)}",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text("By switching to balanced home-made feed.", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
