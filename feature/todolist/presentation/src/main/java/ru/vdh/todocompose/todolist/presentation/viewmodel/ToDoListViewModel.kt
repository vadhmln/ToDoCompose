package ru.vdh.todocompose.todolist.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.core.presentation.viewmodel.SharedViewModel
import ru.vdh.todocompose.todolist.domain.usecase.GetAllTasksUseCase
import ru.vdh.todocompose.todolist.presentation.mapper.ToDoListDomainToPresentationMapper
import ru.vdh.todocompose.todolist.presentation.model.SearchAppBarState
import ru.vdh.todocompose.todolist.presentation.model.ToDoListState
import ru.vdh.todocompose.todolist.presentation.model.ToDoTaskPresentationModel
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val toDoListDomainToPresentationMapper: ToDoListDomainToPresentationMapper,
) : SharedViewModel() {

    var searchAppBarState by mutableStateOf(SearchAppBarState.CLOSED)
        private set
    var searchTextState by mutableStateOf("")
        private set

    private val _allTasks =
        MutableStateFlow<ToDoListState<List<ToDoTaskPresentationModel?>>>(ToDoListState.Idle)
    val allTasks: StateFlow<ToDoListState<List<ToDoTaskPresentationModel?>>> = _allTasks

    private val getAllTasks: Flow<List<ToDoTaskPresentationModel?>> =
        getAllTasksUseCase.invoke().map { list ->
            list.map(toDoListDomainToPresentationMapper::toPresentation)
        }

    init {
        getAllTasks()
        Log.e("AAA", "ToDoListViewModel created!!!")
    }

    fun persistSortState(priority: String) {
        viewModelScope.launch(Dispatchers.IO) {
//            dataStoreRepository.persistSortState(priority = priority)
        }
    }

    private fun getAllTasks() {
        _allTasks.value = ToDoListState.Loading
        try {
            viewModelScope.launch {
                getAllTasks.collect {
                    _allTasks.value = ToDoListState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = ToDoListState.Error(e)
        }
    }

    fun updateAppBarState(newState: SearchAppBarState) {
        searchAppBarState = newState
    }

    fun updateSearchText(newText: String) {
        searchTextState = newText
    }

}


