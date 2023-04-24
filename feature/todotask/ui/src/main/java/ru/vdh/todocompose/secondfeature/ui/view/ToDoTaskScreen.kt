package ru.vdh.todocompose.secondfeature.ui.view

import android.annotation.SuppressLint
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
import androidx.navigation.NavController
import ru.vdh.cleanarch.navigation.Screen
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.secondfeature.presentation.model.ToDoTaskPresentationModel
import ru.vdh.todocompose.secondfeature.presentation.viewmodel.ToDoTaskViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoTaskScreen(
    selectedTask: ToDoTaskPresentationModel?,
    navigateToListScreen: (Action) -> Unit,
    toDoTaskViewModel: ToDoTaskViewModel
) {

    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Second Feature")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
            )
        }
    ) {
    }
}


