package ru.vdh.todocompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.vdh.cleanarch.navigation.Screen
import ru.vdh.todocompose.todolist.navigation.todoListComposable
import ru.vdh.todocompose.secondfeature.navigation.todoTaskComposable
import ru.vdh.todocompose.secondfeature.presentation.viewmodel.ToDoTaskViewModel
import ru.vdh.todocompose.todolist.presentation.viewmodel.ToDoListViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBackClick: () -> Unit,
    startDestination: String = Screen.ToDoListScreen.route,
    toDoListViewModel: ToDoListViewModel,
    toDoTaskViewModel: ToDoTaskViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        todoListComposable(navController, toDoListViewModel)
        todoTaskComposable(navController, toDoTaskViewModel)
    }
}