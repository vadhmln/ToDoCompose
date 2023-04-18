package ru.vdh.todocompose.todolist.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.vdh.cleanarch.navigation.Screen
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.common.utils.Constants.LIST_ARGUMENT_KEY
import ru.vdh.todocompose.common.utils.toAction
import ru.vdh.todocompose.todolist.presentation.viewmodel.ToDoListViewModel
import ru.vdh.todocompose.todolist.ui.view.ToDoListScreen

fun NavGraphBuilder.todoListComposable(
    navController: NavController,
    toDoListViewModel: ToDoListViewModel
) {
    composable(
        route = Screen.ToDoListScreen.route,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }

        LaunchedEffect(key1 = myAction) {
            if(action != myAction){
                myAction = action
                toDoListViewModel.updateAction(newAction = action)
            }
        }

        val databaseAction = toDoListViewModel.action

        ToDoListScreen(databaseAction, navController, toDoListViewModel)
    }
}