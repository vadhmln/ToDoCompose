package ru.vdh.todocompose.todolist.ui.view

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import ru.vdh.cleanarch.navigation.Screen
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.todolist.presentation.viewmodel.ToDoListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    action: Action,
    navController: NavController,
    toDoListViewModel: ToDoListViewModel
) {
    LaunchedEffect(key1 = action) {
        toDoListViewModel.handleDatabaseActions(action = action)
    }

    val allTasks by toDoListViewModel.allTasks.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.ToDoTaskScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "To Do")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
            )
        }
    ) {
    }
}


