package ru.vdh.todocompose.todotask.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.vdh.cleanarch.navigation.Screen
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.core.presentation.viewmodel.SharedViewModel
import ru.vdh.todocompose.todotask.components.TaskAppBar
import ru.vdh.todocompose.todotask.components.TaskContent
import ru.vdh.todocompose.todotask.presentation.model.ToDoTaskPresentationModel
import ru.vdh.todocompose.todotask.presentation.viewmodel.ToDoTaskViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToDoTaskScreen(
    selectedTask: ToDoTaskPresentationModel?,
    navigateToListScreen: (Action) -> Unit,
    toDoTaskViewModel: ToDoTaskViewModel,
) {

    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    val title: String = toDoTaskViewModel.title
    val description: String = toDoTaskViewModel.description
    val priority = toDoTaskViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (toDoTaskViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = { title ->
                    toDoTaskViewModel.updateTitle(title)
                },
                description = description,
                onDescriptionChange = { description ->
                    toDoTaskViewModel.updateDescription(newDescription = description)
                },
                priority = priority,
                onPrioritySelected = { priority ->
                    toDoTaskViewModel.updatePriority(newPriority = priority)
                },
                paddingValues = it
            )
                Log.d("TaskContentTitle", title)
        }
    )
}

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty.",
        Toast.LENGTH_SHORT
    ).show()
}


