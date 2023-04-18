package ru.vdh.todocompose.todolist.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel

interface ToDoListDataSource {

    fun getAllTasks(): Flow<List<ToDoTaskDataModel>>

    fun sortByLowPriority(): Flow<List<ToDoTaskDataModel>>

    fun sortByHighPriority(): Flow<List<ToDoTaskDataModel>>
}