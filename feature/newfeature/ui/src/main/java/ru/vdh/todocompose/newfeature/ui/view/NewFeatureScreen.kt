package ru.vdh.todocompose.newfeature.ui.view

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import ru.vdh.cleanarch.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFeatureScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.SecondFeatureScreen.route)
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
                    Text(text = "New Feature")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                )
            )
        }
    ) {
    }
}


