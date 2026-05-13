package com.example.pashuaahar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pashuaahar.ui.*
import com.example.pashuaahar.ui.theme.PashuAaharTheme
import com.example.pashuaahar.logic.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PashuAaharTheme {
                MainScreen()
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Recipe : Screen("recipe", "Recipe", Icons.Default.Home)
    object Cost : Screen("cost", "Savings", Icons.AutoMirrored.Filled.List)
    object Tips : Screen("tips", "Tips", Icons.Default.Info)
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // Shared ViewModel for the whole app session
    val sharedViewModel: MainViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(Screen.Profile, Screen.Recipe, Screen.Cost, Screen.Tips)
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Profile.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Profile.route) { 
                ProfileScreen(navController, sharedViewModel) 
            }
            composable(Screen.Recipe.route) { 
                RecipeScreen(sharedViewModel) 
            }
            composable(Screen.Cost.route) { 
                CostTrackerScreen(sharedViewModel) 
            }
            composable(Screen.Tips.route) { 
                TipsScreen() 
            }
        }
    }
}
