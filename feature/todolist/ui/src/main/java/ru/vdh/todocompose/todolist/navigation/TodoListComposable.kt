package ru.vdh.todocompose.todolist.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.common.utils.Constants
import ru.vdh.todocompose.common.utils.Constants.LIST_ARGUMENT_KEY
import ru.vdh.todocompose.common.utils.Constants.LIST_SCREEN
import ru.vdh.todocompose.common.utils.toAction
import ru.vdh.todocompose.todolist.presentation.viewmodel.ToDoListViewModel
import ru.vdh.todocompose.todolist.ui.view.ToDoListScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.todoListComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    toDoListViewModel: ToDoListViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        Log.d("todoListComposable", action.name)

        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }

        LaunchedEffect(key1 = myAction) {
            if (action != myAction) {
                myAction = action
                toDoListViewModel.updateAction(newAction = action)
            }
        }

        val databaseAction = toDoListViewModel.action

        ToDoListScreen(
            action = databaseAction,
            navigateToTaskScreen = navigateToTaskScreen,
            toDoListViewModel = toDoListViewModel
        )
    }
}