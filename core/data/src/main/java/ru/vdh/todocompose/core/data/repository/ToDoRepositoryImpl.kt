package ru.vdh.todocompose.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.todocompose.core.data.datasource.ToDoDataSource
import ru.vdh.todocompose.core.data.mapper.ToDoListDataToDomainMapper
import ru.vdh.todocompose.core.data.mapper.ToDoListDomainToDataMapper
import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.core.domain.model.ToDoTaskDomainModel
import ru.vdh.todocompose.core.domain.repository.ToDoRepository

class ToDoRepositoryImpl (
    private val toDoDataSource: ToDoDataSource,
    private val toDoListDataToDomainMapper: ToDoListDataToDomainMapper,
    private val toDoListDomainToDataMapper: ToDoListDomainToDataMapper
) : ToDoRepository {

    override val getAllTasks: Flow<List<ToDoTaskDomainModel>> =
        toDoDataSource.getAllTasks().map { list ->
            list.map(toDoListDataToDomainMapper::toDomain)
        }

    override val sortByLowPriority: Flow<List<ToDoTaskDomainModel>> =
        toDoDataSource.sortByLowPriority().map { list ->
            list.map(toDoListDataToDomainMapper::toDomain)
        }

    override val sortByHighPriority: Flow<List<ToDoTaskDomainModel>> =
        toDoDataSource.sortByHighPriority().map { list ->
            list.map(toDoListDataToDomainMapper::toDomain)
        }

    override fun getSelectedTask(taskId: Int): Flow<ToDoTaskDomainModel> =
         toDoDataSource.getSelectedTask(taskId = taskId).map(toDoListDataToDomainMapper::toDomain)


    override suspend fun addTask(toDoTask: ToDoTaskDomainModel) {
        val toDo = toDoListDomainToDataMapper.toData(toDoTask)
        toDoDataSource.addTask(toDoTask = toDo)
    }

    override suspend fun updateTask(toDoTask: ToDoTaskDomainModel) {
        val toDo = toDoListDomainToDataMapper.toData(toDoTask)
        toDoDataSource.updateTask(toDoTask = toDo)
    }

    override suspend fun deleteTask(toDoTask: ToDoTaskDomainModel) {
        val toDo = toDoListDomainToDataMapper.toData(toDoTask)
        toDoDataSource.deleteTask(toDoTask = toDo)
    }

    override suspend fun deleteAllTasks() {
        toDoDataSource.deleteAllTasks()
    }

    override fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskDomainModel>> =
         toDoDataSource.searchDatabase(searchQuery = searchQuery).map { list ->
             list.map(toDoListDataToDomainMapper::toDomain)
         }
}