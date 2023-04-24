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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.todolist.presentation.model.SearchAppBarState
import ru.vdh.todocompose.todolist.presentation.viewmodel.ToDoListViewModel
import ru.vdh.todocompose.todolist.ui.components.ListAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    action: Action?,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    toDoListViewModel: ToDoListViewModel
) {
    val searchAppBarState: SearchAppBarState = toDoListViewModel.searchAppBarState
    val searchTextState: String = toDoListViewModel.searchTextState

    LaunchedEffect(key1 = action) {
        if (action != null) {
            toDoListViewModel.handleDatabaseActions(action = action)
        }
    }

//    val allTasks by toDoListViewModel.allTasks.collectAsState()

    Scaffold(
        topBar = {
            ListAppBar(
                toDoListViewModel = toDoListViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        },
    ) {
    }
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(
                id = ru.vdh.cleanarch.core.ui.R.string.add_button
            ),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
@Preview
private fun ToDoListPreview() {
//    ToDoListScreen(action = null, navigateToTaskScreen = {}, toDoListViewModel = null)
}


