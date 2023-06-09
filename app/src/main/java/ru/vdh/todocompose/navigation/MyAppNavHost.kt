package ru.vdh.todocompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.vdh.cleanarch.navigation.Screens
import ru.vdh.todocompose.common.utils.Constants.LIST_SCREEN
import ru.vdh.todocompose.todolist.navigation.listComposable
import ru.vdh.todocompose.todolist.navigation.todoTaskComposable
import ru.vdh.todocompose.todolist.presentation.viewmodel.SharedViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onBackClick: () -> Unit,
    sharedViewModel: SharedViewModel,
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel,
        )
        todoTaskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel,
            navController
        )
    }
}