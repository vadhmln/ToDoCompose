package ru.vdh.todocompose.todolist.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.common.utils.Constants.TASK_ARGUMENT_KEY
import ru.vdh.todocompose.common.utils.Constants.TASK_SCREEN
import ru.vdh.todocompose.todolist.presentation.viewmodel.SharedViewModel
import ru.vdh.todocompose.todolist.ui.view.TaskScreen

fun NavGraphBuilder.todoTaskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel,
    navController: NavHostController
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}