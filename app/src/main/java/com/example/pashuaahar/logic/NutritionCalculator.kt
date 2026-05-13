package com.example.pashuaahar.logic

import com.example.pashuaahar.data.*

object NutritionCalculator {

    // Market rates per kg (Approximate)
    private const val COST_MAIZE = 25.0
    private const val COST_COTTONSEED_CAKE = 40.0
    private const val COST_WHEAT_BRAN = 20.0
    private const val COST_MARKET_FEED = 45.0

    fun calculateRecipe(profile: CowProfile): Recipe {
        // Basic Logic:
        // Maintenance: 1kg concentrate
        // Production: 
        // - Jersey: 1kg per 2.0kg milk
        // - Desi: 1kg per 2.5kg milk
        
        val maintenanceConcentrate = 1.0
        val productionConcentrate = when (profile.breed) {
            CowBreed.JERSEY -> profile.dailyMilkYieldLiters / 2.0
            CowBreed.DESI -> profile.dailyMilkYieldLiters / 2.5
        }
        
        val totalConcentrate = maintenanceConcentrate + productionConcentrate
        
        // Typical Mix: 40% Maize, 30% Cake, 30% Bran
        val maize = totalConcentrate * 0.4
        val cake = totalConcentrate * 0.3
        val bran = totalConcentrate * 0.3
        
        val ingredients = listOf(
            FeedIngredient("Maize (Energy)", maize),
            FeedIngredient("Cottonseed Cake (Protein)", cake),
            FeedIngredient("Wheat Bran (Fiber)", bran)
        )
        
        val costHomeMade = (maize * COST_MAIZE) + (cake * COST_COTTONSEED_CAKE) + (bran * COST_WHEAT_BRAN)
        val costMarket = totalConcentrate * COST_MARKET_FEED
        
        return Recipe(
            totalConcentrateKg = totalConcentrate,
            ingredients = ingredients,
            estimatedCostHomeMade = costHomeMade,
            estimatedCostMarket = costMarket
        )
    }
}
