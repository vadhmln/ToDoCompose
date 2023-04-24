package ru.vdh.cleanarch.navigation

import androidx.navigation.NavHostController
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.common.utils.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    val list: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
}