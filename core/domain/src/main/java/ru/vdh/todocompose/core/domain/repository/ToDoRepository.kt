package ru.vdh.todocompose.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.core.domain.model.ToDoTaskDomainModel

interface ToDoRepository {

    val getAllTasks: Flow<List<ToDoTaskDomainModel?>>
    val sortByLowPriority: Flow<List<ToDoTaskDomainModel?>>
    val sortByHighPriority: Flow<List<ToDoTaskDomainModel?>>

    fun getSelectedTask(taskId: Int): Flow<ToDoTaskDomainModel?>

    suspend fun addTask(toDoTask: ToDoTaskDomainModel)

    suspend fun updateTask(toDoTask: ToDoTaskDomainModel)

    suspend fun deleteTask(toDoTask: ToDoTaskDomainModel)

    suspend fun deleteAllTasks()

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskDomainModel?>>
}