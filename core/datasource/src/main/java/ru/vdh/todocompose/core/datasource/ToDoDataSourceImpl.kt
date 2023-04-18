package ru.vdh.todocompose.core.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.todocompose.core.datasource.mapper.DataBaseToToDoListDataMapper
import ru.vdh.todocompose.core.datasource.mapper.ToDoDataToDataBaseMapper
import ru.vdh.todocompose.core.data.datasource.ToDoDataSource
import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.core.database.dao.ToDoDao

class ToDoDataSourceImpl(
    private val dataBaseToToDoListDataMapper: DataBaseToToDoListDataMapper,
    private val toDoDataToDataBaseMapper: ToDoDataToDataBaseMapper,
    private val toDoDao: ToDoDao,
) : ToDoDataSource {

    override fun getAllTasks(): Flow<List<ToDoTaskDataModel>> =
        toDoDao.getAllTasks().map { list ->
            list.map(dataBaseToToDoListDataMapper::toData)
        }

    override fun sortByLowPriority(): Flow<List<ToDoTaskDataModel>> =
        toDoDao.sortByLowPriority().map { list ->
            list.map(dataBaseToToDoListDataMapper::toData)
        }

    override fun sortByHighPriority(): Flow<List<ToDoTaskDataModel>> =
        toDoDao.sortByHighPriority().map { list ->
            list.map(dataBaseToToDoListDataMapper::toData)
        }

    override fun getSelectedTask(taskId: Int): Flow<ToDoTaskDataModel> =
         toDoDao.getSelectedTask(taskId = taskId).map(dataBaseToToDoListDataMapper::toData)


    override suspend fun addTask(toDoTask: ToDoTaskDataModel) {
        val toDo = toDoDataToDataBaseMapper.toDataBase(toDoTask)
        toDoDao.addTask(toDoTask = toDo)
    }

    override suspend fun updateTask(toDoTask: ToDoTaskDataModel) {
        val toDo = toDoDataToDataBaseMapper.toDataBase(toDoTask)
        toDoDao.updateTask(toDoTask = toDo)
    }

    override suspend fun deleteTask(toDoTask: ToDoTaskDataModel) {
        val toDo = toDoDataToDataBaseMapper.toDataBase(toDoTask)
        toDoDao.deleteTask(toDoTask = toDo)
    }

    override suspend fun deleteAllTasks() {
        toDoDao.deleteAllTasks()
    }

    override fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskDataModel>> =
       toDoDao.searchDatabase(searchQuery = searchQuery).map { list ->
           list.map(dataBaseToToDoListDataMapper::toData)
       }
}