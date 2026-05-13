package com.example.pashuaahar.logic

import androidx.lifecycle.ViewModel
import com.example.pashuaahar.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _cowProfile = MutableStateFlow(CowProfile())
    val cowProfile: StateFlow<CowProfile> = _cowProfile.asStateFlow()

    private val _recipe = MutableStateFlow(NutritionCalculator.calculateRecipe(_cowProfile.value))
    val recipe: StateFlow<Recipe> = _recipe.asStateFlow()

    fun updateProfile(newProfile: CowProfile) {
        _cowProfile.value = newProfile
        _recipe.value = NutritionCalculator.calculateRecipe(newProfile)
    }
}
