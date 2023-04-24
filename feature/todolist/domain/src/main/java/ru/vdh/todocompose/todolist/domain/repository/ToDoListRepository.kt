package ru.vdh.todocompose.todolist.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.todolist.domain.model.ToDoTaskDomainModel

interface ToDoListRepository {

    fun getAllTasks(): Flow<List<ToDoTaskDomainModel?>>

    fun sortByLowPriority(): Flow<List<ToDoTaskDomainModel?>>

    fun sortByHighPriority(): Flow<List<ToDoTaskDomainModel?>>
}