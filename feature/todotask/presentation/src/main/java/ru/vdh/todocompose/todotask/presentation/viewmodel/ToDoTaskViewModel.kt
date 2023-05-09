package ru.vdh.todocompose.todotask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vdh.todocompose.common.utils.Constants.MAX_TITLE_LENGTH
import ru.vdh.todocompose.core.presentation.viewmodel.SharedViewModel
import ru.vdh.todocompose.secondfeature.domain.usecase.GetSelectedTaskUseCase
import ru.vdh.todocompose.todotask.presentation.mapper.ToDoTaskDomainToPresentationMapper
import ru.vdh.todocompose.todotask.presentation.mapper.ToDoTaskPresentationToDomainMapper
import ru.vdh.todocompose.todotask.presentation.model.ToDoTaskPresentationModel
import javax.inject.Inject

@HiltViewModel
class ToDoTaskViewModel @Inject constructor(
    private val getSelectedTaskUseCase: GetSelectedTaskUseCase,
    private val toDoTaskDomainToPresentationMapper: ToDoTaskDomainToPresentationMapper,
) : SharedViewModel() {

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
            priority = selectedTask.priority
        } else {
            id = 0
            title = ""
            description = ""
            priority = "LOW"
        }
    }

    fun validateFields(): Boolean {
        return title.isNotEmpty() && description.isNotEmpty()
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title = newTitle
            Log.d("TaskContent", "title - $title")
        }
    }

    fun updateDescription(newDescription: String) {
        description = newDescription
        Log.d("TaskContent", "description - $description")
    }

    fun updatePriority(newPriority: String) {
        priority = newPriority
    }
}