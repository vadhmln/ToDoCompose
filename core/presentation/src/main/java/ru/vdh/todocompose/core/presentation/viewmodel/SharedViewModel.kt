package ru.vdh.todocompose.core.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vdh.todocompose.common.utils.Action
import javax.inject.Inject

abstract class SharedViewModel : ViewModel() {

    var action by mutableStateOf(Action.NO_ACTION)
        private set

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
//                addTask()
            }
            Action.UPDATE -> {
//                updateTask()
            }
            Action.DELETE -> {
//                deleteTask()
            }
            Action.DELETE_ALL -> {
//                deleteAllTasks()
            }
            Action.UNDO -> {
//                addTask()
            }
            else -> {

            }
        }
    }

    fun updateAction(newAction: Action) {
        action = newAction
    }
}


