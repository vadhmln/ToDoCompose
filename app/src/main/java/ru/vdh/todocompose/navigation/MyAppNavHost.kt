package ru.vdh.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.vdh.cleanarch.navigation.Screen
import ru.vdh.todocompose.newfeature.navigation.newFeatureScreen
import ru.vdh.todocompose.secondfeature.navigation.secondFeatureScreen

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onBackClick: () -> Unit,
    startDestination: String = Screen.NewFeatureScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        newFeatureScreen(navController)
        secondFeatureScreen(navController)
    }
}