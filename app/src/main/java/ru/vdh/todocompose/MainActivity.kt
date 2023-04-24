package ru.vdh.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.todocompose.core.ui.theme.MyApplicationTheme
import ru.vdh.todocompose.navigation.MyAppNavHost
import ru.vdh.todocompose.secondfeature.presentation.viewmodel.ToDoTaskViewModel
import ru.vdh.todocompose.todolist.presentation.viewmodel.ToDoListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val toDoListViewModel: ToDoListViewModel by viewModels()
    private val toDoTaskViewModel: ToDoTaskViewModel by viewModels()
//    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
//                navController = rememberNavController()
                MyAppNavHost(
//                    navController = navController,
                    toDoListViewModel = toDoListViewModel,
                    toDoTaskViewModel = toDoTaskViewModel,
                    onBackClick = {}
                )
            }
        }
    }
}

