package ru.vdh.todocompose.todolist.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.todocompose.todolist.data.datasource.ToDoListDataSource
import ru.vdh.todocompose.todolist.data.mapper.ToDoListDataToDomainMapper
import ru.vdh.todocompose.todolist.domain.model.ToDoTaskDomainModel
import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository

class ToDoListRepositoryImpl(
    private val toDoListDataSource: ToDoListDataSource,
    private val toDoListDataToDomainMapper: ToDoListDataToDomainMapper,
) : ToDoListRepository {

    override fun getAllTasks(): Flow<List<ToDoTaskDomainModel>> =
        toDoListDataSource.getAllTasks().map { list ->
            list.map(toDoListDataToDomainMapper::toDomain)
        }

    override fun sortByLowPriority(): Flow<List<ToDoTaskDomainModel>> =
        toDoListDataSource.sortByLowPriority().map { list ->
            list.map(toDoListDataToDomainMapper::toDomain)
        }

    override fun sortByHighPriority(): Flow<List<ToDoTaskDomainModel>> =
        toDoListDataSource.sortByHighPriority().map { list ->
            list.map(toDoListDataToDomainMapper::toDomain)
        }
}





