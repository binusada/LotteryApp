package com.mykodo.ui.navigation

sealed class Screen (val route: String) {
    object MainScreen : Screen( "main_screen")
    object DetailsScreen : Screen("detailed_screen") {
        const val argumentId: String = "id"
    }
}