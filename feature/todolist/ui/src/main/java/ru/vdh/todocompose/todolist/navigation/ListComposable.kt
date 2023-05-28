package ru.vdh.todocompose.todolist.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.vdh.todocompose.common.utils.Constants.LIST_ARGUMENT_KEY
import ru.vdh.todocompose.common.utils.Constants.LIST_SCREEN
import ru.vdh.todocompose.common.utils.toAction
import ru.vdh.todocompose.todolist.presentation.viewmodel.SharedViewModel
import ru.vdh.todocompose.todolist.ui.view.ListScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        Log.d("Action", "$action")

        LaunchedEffect(key1 = action) {
            sharedViewModel.onUpdateAction(newAction = action)
        }

        val databaseAction = sharedViewModel.action

        ListScreen(
            action = databaseAction,
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}