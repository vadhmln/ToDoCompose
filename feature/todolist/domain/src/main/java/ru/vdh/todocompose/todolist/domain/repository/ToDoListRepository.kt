package ru.vdh.todocompose.todolist.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.todolist.domain.model.ToDoTaskDomainModel

interface ToDoListRepository {

    fun getAllTasks(): Flow<List<ToDoTaskDomainModel?>>

    fun getSelectedTask(taskId: Int): Flow<ToDoTaskDomainModel?>

    fun sortByLowPriority(): Flow<List<ToDoTaskDomainModel?>>

    fun sortByHighPriority(): Flow<List<ToDoTaskDomainModel?>>

    suspend fun addTask(toDoTask: ToDoTaskDomainModel)

    suspend fun updateTask(toDoTask: ToDoTaskDomainModel)

    suspend fun deleteTask(toDoTask: ToDoTaskDomainModel)

    suspend fun deleteAllTasks()

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskDomainModel?>>
}