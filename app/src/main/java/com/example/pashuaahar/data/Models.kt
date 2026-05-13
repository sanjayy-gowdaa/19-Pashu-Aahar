package com.example.pashuaahar.data

enum class CowBreed(val displayName: String) {
    JERSEY("Jersey"),
    DESI("Desi (Indigenous)")
}

data class CowProfile(
    val breed: CowBreed = CowBreed.DESI,
    val weightKg: Double = 400.0,
    val dailyMilkYieldLiters: Double = 10.0
)

data class FeedIngredient(
    val name: String,
    val ratio: Double, // Amount in kg
    val iconResId: Int? = null
)

data class Recipe(
    val totalConcentrateKg: Double,
    val ingredients: List<FeedIngredient>,
    val estimatedCostMarket: Double,
    val estimatedCostHomeMade: Double
)
