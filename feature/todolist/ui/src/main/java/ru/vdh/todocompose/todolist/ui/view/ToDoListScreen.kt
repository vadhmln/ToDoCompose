package ru.vdh.todocompose.todolist.ui.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.todolist.presentation.model.SearchAppBarState
import ru.vdh.todocompose.todolist.presentation.viewmodel.ToDoListViewModel
import ru.vdh.todocompose.todolist.ui.components.ListAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ToDoListScreen(
    action: Action,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    toDoListViewModel: ToDoListViewModel
) {

    LaunchedEffect(key1 = action) {
        toDoListViewModel.handleDatabaseActions(action = action)
        Log.d("ToDoListScreen", "LaunchedEffect triggered!")
    }

    val allTasks by toDoListViewModel.allTasks.collectAsState()
    val searchedTasks by toDoListViewModel.searchedTasks.collectAsState()
    val sortState by toDoListViewModel.sortState.collectAsState()
    val lowPriorityTasks by toDoListViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by toDoListViewModel.highPriorityTasks.collectAsState()

    val searchAppBarState: SearchAppBarState = toDoListViewModel.searchAppBarState
    val searchTextState: String = toDoListViewModel.searchTextState

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            ListAppBar(
                toDoListViewModel = toDoListViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                allTasks = allTasks,
                searchedTasks = searchedTasks,
                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                sortState = sortState,
                searchAppBarState = searchAppBarState,
                onSwipeToDelete = { action, task ->
                    toDoListViewModel.updateAction(newAction = action)
                    toDoListViewModel.updateTaskFields(selectedTask = task)
                    snackbarHostState.currentSnackbarData?.dismiss()
                },
                navigateToTaskScreen = navigateToTaskScreen,
                paddingValues = it
            )
            Log.d("ToDoListScreen", "$allTasks")
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        },
    )

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

