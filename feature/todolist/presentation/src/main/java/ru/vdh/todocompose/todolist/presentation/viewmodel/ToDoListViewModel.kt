package ru.vdh.todocompose.todolist.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.core.presentation.viewmodel.SharedViewModel
import ru.vdh.todocompose.todolist.domain.usecase.AddTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.GetAllTasksUseCase
import ru.vdh.todocompose.todolist.domain.usecase.GetSelectedTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SortByHighPriorityUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SortByLowPriorityUseCase
import ru.vdh.todocompose.todolist.presentation.mapper.ToDoListDomainToPresentationMapper
import ru.vdh.todocompose.todolist.presentation.mapper.ToDoListPresentationToDomainMapper
import ru.vdh.todocompose.todolist.presentation.model.RequestState
import ru.vdh.todocompose.todolist.presentation.model.SearchAppBarState
import ru.vdh.todocompose.todolist.presentation.model.ToDoTaskPresentationModel
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val sortByLowPriorityUseCase: SortByLowPriorityUseCase,
    private val sortByHighPriorityUseCase: SortByHighPriorityUseCase,
    private val getSelectedTaskUseCase: GetSelectedTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val toDoListDomainToPresentationMapper: ToDoListDomainToPresentationMapper,
    private val toDoListPresentationToDomainMapper: ToDoListPresentationToDomainMapper,
) : SharedViewModel() {

    var searchAppBarState by mutableStateOf(SearchAppBarState.CLOSED)
        private set
    var searchTextState by mutableStateOf("")
        private set

    private val _allTasks =
        MutableStateFlow<RequestState<List<ToDoTaskPresentationModel?>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTaskPresentationModel?>>> = _allTasks

    private val _searchedTasks =
        MutableStateFlow<RequestState<List<ToDoTaskPresentationModel>>>(RequestState.Idle)
    val searchedTasks: StateFlow<RequestState<List<ToDoTaskPresentationModel>>> = _searchedTasks

    private val _selectedTask: MutableStateFlow<ToDoTaskPresentationModel?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTaskPresentationModel?> = _selectedTask

    private val _sortState =
        MutableStateFlow<RequestState<String>>(RequestState.Idle)
    val sortState: StateFlow<RequestState<String>> = _sortState

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

    val lowPriorityTasks: StateFlow<List<ToDoTaskPresentationModel?>> =
        sortByLowPriorityUseCase.invoke().map { list ->
            list.map(toDoListDomainToPresentationMapper::toPresentation)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val highPriorityTasks: StateFlow<List<ToDoTaskPresentationModel?>> =
        sortByHighPriorityUseCase.invoke().map { list ->
            list.map(toDoListDomainToPresentationMapper::toPresentation)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            getSelectedTaskUseCase.invoke(taskId = taskId)
                .map(toDoListDomainToPresentationMapper::toPresentation).collect { task ->
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

    fun updateAppBarState(newState: SearchAppBarState) {
        searchAppBarState = newState
    }

    fun updateSearchText(newText: String) {
        searchTextState = newText
    }

    var action by mutableStateOf(Action.NO_ACTION)
        private set

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTaskPresentationModel(
                id = id,
                title = title,
                description = description,
                priority = priority,
                date = System.currentTimeMillis()
            )
            addTaskUseCase.invoke(toDoTask = toDoListPresentationToDomainMapper.toDomain(toDoTask))
            Log.d("AddTask", "$toDoTask")
        }
//        searchAppBarState = SearchAppBarState.CLOSED
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTaskPresentationModel(
                id = id,
                title = title,
                description = description,
                priority = priority,
                date = date
            )
//            repository.updateTask(toDoTask = toDoTask)
        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTaskPresentationModel(
                id = id,
                title = title,
                description = description,
                priority = priority,
                date = date
            )
//            repository.deleteTask(toDoTask = toDoTask)
        }
    }

    private fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteAllTasks()
        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
                Log.d("AddTask", "addTask()!!!")
            }

            Action.UPDATE -> {
                updateTask()
            }

            Action.DELETE -> {
                deleteTask()
            }

            Action.DELETE_ALL -> {
                deleteAllTasks()
            }

            Action.UNDO -> {
                addTask()
            }

            else -> {
                Action.NO_ACTION
            }
        }
    }

    fun updateAction(newAction: Action) {
        action = newAction
    }
}


