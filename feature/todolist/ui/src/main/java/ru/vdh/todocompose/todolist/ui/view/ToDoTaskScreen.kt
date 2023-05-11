package ru.vdh.todocompose.todolist.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.todolist.presentation.model.ToDoTaskPresentationModel
import ru.vdh.todocompose.todolist.presentation.viewmodel.SharedViewModel
import ru.vdh.todocompose.todolist.ui.components.TaskAppBar
import ru.vdh.todocompose.todolist.ui.components.TaskContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToDoTaskScreen(
    selectedTask: ToDoTaskPresentationModel?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel,
) {

    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    val title: String = sharedViewModel.title
    val description: String = sharedViewModel.description
    val priority = sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
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
                    sharedViewModel.onTitleUpdate(title)
                },
                description = description,
                onDescriptionChange = { description ->
                    sharedViewModel.onDescriptionUpdate(description)
                },
                priority = priority,
                onPrioritySelected = { priority ->
                    sharedViewModel.onPriorityUpdate(priority)
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


