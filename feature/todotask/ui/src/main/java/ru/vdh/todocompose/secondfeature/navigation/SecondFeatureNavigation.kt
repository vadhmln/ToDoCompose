package ru.vdh.todocompose.secondfeature.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import ru.vdh.cleanarch.navigation.Screen
import ru.vdh.todocompose.common.utils.Constants.TASK_ARGUMENT_KEY
import ru.vdh.todocompose.secondfeature.presentation.viewmodel.ToDoTaskViewModel
import ru.vdh.todocompose.secondfeature.ui.view.ToDoTaskScreen

const val secondFeatureRoute = "second_feature_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.todoTaskComposable(
    navController: NavController,
    toDoTaskViewModel: ToDoTaskViewModel
) {
    composable(
        route = Screen.ToDoTaskScreen.route,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            toDoTaskViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by toDoTaskViewModel.selectedTask.collectAsState()
        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                toDoTaskViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        ToDoTaskScreen(navController)
    }
}