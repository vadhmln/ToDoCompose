package ru.vdh.todocompose.todolist.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.vdh.todocompose.common.utils.Action
import ru.vdh.todocompose.common.utils.Constants.MAX_TITLE_LENGTH
import ru.vdh.todocompose.todolist.domain.usecase.AddTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.DeleteAllTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.DeleteTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.GetAllTasksUseCase
import ru.vdh.todocompose.todolist.domain.usecase.GetSelectedTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.PersistSortStateUseCase
import ru.vdh.todocompose.todolist.domain.usecase.ReadSortStateUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SearchTasksUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SortByHighPriorityUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SortByLowPriorityUseCase
import ru.vdh.todocompose.todolist.domain.usecase.UpdateTaskUseCase
import ru.vdh.todocompose.todolist.presentation.mapper.ToDoListDomainToPresentationMapper
import ru.vdh.todocompose.todolist.presentation.mapper.ToDoListPresentationToDomainMapper
import ru.vdh.todocompose.todolist.presentation.model.RequestState
import ru.vdh.todocompose.todolist.presentation.model.SearchAppBarState
import ru.vdh.todocompose.todolist.presentation.model.ToDoTaskPresentationModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val sortByLowPriorityUseCase: SortByLowPriorityUseCase,
    private val sortByHighPriorityUseCase: SortByHighPriorityUseCase,
    private val getSelectedTaskUseCase: GetSelectedTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val searchTasksUseCase: SearchTasksUseCase,
    private val deleteAllTaskUseCase: DeleteAllTaskUseCase,
    private val readSortStateUseCase: ReadSortStateUseCase,
    private val persistSortStateUseCase: PersistSortStateUseCase,
    private val toDoListDomainToPresentationMapper: ToDoListDomainToPresentationMapper,
    private val toDoListPresentationToDomainMapper: ToDoListPresentationToDomainMapper,
) : ViewModel() {

    var action by mutableStateOf(Action.NO_ACTION)
        private set

    val id: MutableState<Int> = mutableStateOf(0)
    var title: MutableState<String> = mutableStateOf("")
    var description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<String> = mutableStateOf("LOW")
    var date by mutableStateOf(0L)
        private set

    var searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
    var searchTextState: MutableState<String> = mutableStateOf("")

    private val _searchedTasks =
        MutableStateFlow<RequestState<List<ToDoTaskPresentationModel?>>>(RequestState.Idle)
    val searchedTasks: StateFlow<RequestState<List<ToDoTaskPresentationModel?>>> = _searchedTasks

    fun searchDatabase(searchQuery: String) {
        _searchedTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                searchTasksUseCase.invoke(searchQuery = "%$searchQuery%").map { list ->
                    list.map(toDoListDomainToPresentationMapper::toPresentation)
                }.collect { searchedTasks ->
                    _searchedTasks.value = RequestState.Success(searchedTasks)
                }
            }
        } catch (e: Exception) {
            _searchedTasks.value = RequestState.Error(e)
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
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

    private val _sortState =
        MutableStateFlow<RequestState<String>>(RequestState.Idle)
    val sortState: StateFlow<RequestState<String>> = _sortState

    fun readSortState() {
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch {
                readSortStateUseCase.invoke()
                    .collect {
                        _sortState.value = RequestState.Success(it)
                    }
            }
        } catch (e: Exception) {
            _sortState.value = RequestState.Error(e)
        }
    }

    fun persistSortState(priority: String) {
        viewModelScope.launch(Dispatchers.IO) {
            persistSortStateUseCase.invoke(priority = priority)
        }
    }

    private val _allTasks =
        MutableStateFlow<RequestState<List<ToDoTaskPresentationModel?>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTaskPresentationModel?>>> = _allTasks

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                getAllTasksUseCase.invoke().map { list ->
                    list.map(toDoListDomainToPresentationMapper::toPresentation)
                }.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTaskPresentationModel?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTaskPresentationModel?> = _selectedTask

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            getSelectedTaskUseCase.invoke(taskId = taskId)
                .map(toDoListDomainToPresentationMapper::toPresentation)
                .collect { task ->
                    _selectedTask.value = task
                }
        }
    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTaskPresentationModel(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value,
                date = System.currentTimeMillis()
            )
            addTaskUseCase.invoke(toDoTask = toDoListPresentationToDomainMapper.toDomain(toDoTask))
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTaskPresentationModel(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value,
                date = System.currentTimeMillis()
            )
            updateTaskUseCase.invoke(toDoTask = toDoListPresentationToDomainMapper.toDomain(toDoTask))
        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTaskPresentationModel(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value,
                date = date
            )
            deleteTaskUseCase.invoke(toDoTask = toDoListPresentationToDomainMapper.toDomain(toDoTask))
        }
    }

    private fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllTaskUseCase.invoke()
        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
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
                Log.d("undo", "trig!!!")
            }

            else -> {

            }
        }
        this.action = Action.NO_ACTION
    }

    fun updateTaskFields(selectedTask: ToDoTaskPresentationModel??) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = "LOW"
        }
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun updateAppBarState(newState: SearchAppBarState) {
        searchAppBarState.value = newState
    }

    fun updateSearchText(newText: String) {
        searchTextState.value = newText
    }

    fun onTitleUpdate(newTitle: String) {
        title.value = newTitle
    }

    fun onDescriptionUpdate(newDescription: String) {
        description.value = newDescription
    }

    fun onPriorityUpdate(newPriority: String) {
        priority.value = newPriority
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

    fun onUpdateAction(newAction: Action) {
        action = newAction
    }

}