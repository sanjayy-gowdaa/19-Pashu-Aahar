package com.example.pashuaahar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Green80 = Color(0xFF8BC34A)
private val GreenGrey80 = Color(0xFFC5E1A5)
private val LightGreen80 = Color(0xFFDCEDC8)

private val Green40 = Color(0xFF4CAF50)
private val GreenGrey40 = Color(0xFF81C784)
private val LightGreen40 = Color(0xFFAED581)

private val DarkColorScheme = darkColorScheme(
    primary = Green80,
    secondary = GreenGrey80,
    tertiary = LightGreen80
)

private val LightColorScheme = lightColorScheme(
    primary = Green40,
    secondary = GreenGrey40,
    tertiary = LightGreen40
)

@Composable
fun PashuAaharTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
