package ru.vdh.todocompose.secondfeature.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vdh.cleanarch.secondfeature.presentation.R
import ru.vdh.todocompose.core.presentation.viewmodel.SharedViewModel
import ru.vdh.todocompose.secondfeature.domain.usecase.GetSelectedTaskUseCase
import ru.vdh.todocompose.secondfeature.presentation.mapper.ToDoTaskDomainToPresentationMapper
import ru.vdh.todocompose.secondfeature.presentation.model.PriorityPresentationModel
import ru.vdh.todocompose.secondfeature.presentation.model.ToDoTaskPresentationModel
import javax.inject.Inject

@HiltViewModel
class ToDoTaskViewModel @Inject constructor(
    private val getSelectedTaskUseCase: GetSelectedTaskUseCase,
    private val toDoTaskDomainToPresentationMapper: ToDoTaskDomainToPresentationMapper
) : SharedViewModel() {

    var id by mutableStateOf(0)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var priority by mutableStateOf(PriorityPresentationModel.LOW)
        private set

    private val _selectedTask: MutableStateFlow<ToDoTaskPresentationModel?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTaskPresentationModel?> = _selectedTask

    init {
        Log.e("AAA", "ToDoTaskViewModel created!!!")
    }

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            getSelectedTaskUseCase.invoke(taskId = taskId)
                .map(toDoTaskDomainToPresentationMapper::toPresentation)
                .collect { task ->
                    _selectedTask.value = task
                }
        }
    }

    fun updateTaskFields(selectedTask: ToDoTaskPresentationModel?) {
        if (selectedTask != null) {
            id = selectedTask.id
            title = selectedTask.title
            description = selectedTask.description
            priority = parsePriority(selectedTask)
        } else {
            id = 0
            title = ""
            description = ""
            priority = PriorityPresentationModel.LOW
        }
    }

    private fun parsePriority(selectedTask: ToDoTaskPresentationModel) =
        when (selectedTask.priority) {
            "High priority" -> PriorityPresentationModel.HIGH

            "Medium priority" -> PriorityPresentationModel.MEDIUM

            "Low priority" -> PriorityPresentationModel.LOW

            else -> PriorityPresentationModel.NONE
        }
}