package ru.vdh.todocompose.todolist.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.core.data.datasource.ToDoDataSource
import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.todolist.datasource.mapper.DataBaseToToDoListDataMapper
import ru.vdh.todocompose.todolist.data.datasource.ToDoListDataSource

class ToDoListDataSourceImpl(
    private val toDoDataSource: ToDoDataSource,
) : ToDoListDataSource {

    override fun getAllTasks(): Flow<List<ToDoTaskDataModel?>> =
        toDoDataSource.getAllTasks()

    override fun sortByLowPriority(): Flow<List<ToDoTaskDataModel?>> =
        toDoDataSource.sortByLowPriority()

    override fun sortByHighPriority(): Flow<List<ToDoTaskDataModel?>> =
        toDoDataSource.sortByHighPriority()
}