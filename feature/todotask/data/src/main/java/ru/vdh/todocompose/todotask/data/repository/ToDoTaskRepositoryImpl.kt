package ru.vdh.todocompose.todotask.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.todocompose.todotask.data.datasource.TaskDataSource
import ru.vdh.todocompose.todotask.data.mapper.ToDoTaskDataToDomainMapper
import ru.vdh.todocompose.secondfeature.domain.model.ToDoTaskDomainModel
import ru.vdh.todocompose.secondfeature.domain.repository.ToDoTaskRepository

class ToDoTaskRepositoryImpl(
    private val taskDataSource: TaskDataSource,
    private val toDoTaskDataToDomainMapper: ToDoTaskDataToDomainMapper,
) : ToDoTaskRepository {

    override fun getSelectedTask(taskId: Int): Flow<ToDoTaskDomainModel?> =
        taskDataSource.getSelectedTask(taskId).map(toDoTaskDataToDomainMapper::toDomain)
}


