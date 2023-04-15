package ru.vdh.todocompose.secondfeature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.vdh.cleanarch.navigation.Screen
import ru.vdh.todocompose.secondfeature.ui.view.SecondFeatureScreen

const val secondFeatureRoute = "second_feature_route"

fun NavGraphBuilder.secondFeatureScreen(navController: NavController) {
    composable(route = Screen.SecondFeatureScreen.route) {
        SecondFeatureScreen(navController)
    }
}