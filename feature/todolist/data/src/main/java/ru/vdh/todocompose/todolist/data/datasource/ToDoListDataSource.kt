package ru.vdh.todocompose.todolist.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.todolist.domain.model.ToDoTaskDomainModel

interface ToDoListDataSource {

    fun getAllTasks(): Flow<List<ToDoTaskDataModel?>>

    fun getSelectedTask(taskId: Int): Flow<ToDoTaskDataModel?>

    fun sortByLowPriority(): Flow<List<ToDoTaskDataModel?>>

    fun sortByHighPriority(): Flow<List<ToDoTaskDataModel?>>

    suspend fun addTask(toDoTask: ToDoTaskDataModel)
}