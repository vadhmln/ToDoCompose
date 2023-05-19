package ru.vdh.todocompose.todolist.datasource

import android.util.Log
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

    override fun getSelectedTask(taskId: Int): Flow<ToDoTaskDataModel?> =
        toDoDataSource.getSelectedTask(taskId)

    override fun sortByLowPriority(): Flow<List<ToDoTaskDataModel?>> =
        toDoDataSource.sortByLowPriority()

    override fun sortByHighPriority(): Flow<List<ToDoTaskDataModel?>> =
        toDoDataSource.sortByHighPriority()

    override suspend fun addTask(toDoTask: ToDoTaskDataModel) {
        toDoDataSource.addTask(toDoTask)
        Log.d("AddTask", "$toDoTask")
    }

    override suspend fun updateTask(toDoTask: ToDoTaskDataModel) {
        toDoDataSource.updateTask(toDoTask)
    }

    override suspend fun deleteTask(toDoTask: ToDoTaskDataModel) {
        toDoDataSource.deleteTask(toDoTask)
    }

    override suspend fun deleteAllTasks() {
        toDoDataSource.deleteAllTasks()
    }

    override fun searchDatabase(searchQuery: String) =
        toDoDataSource.searchDatabase(searchQuery)

}