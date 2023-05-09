package ru.vdh.todocompose.todotask.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.secondfeature.domain.model.ToDoTaskDomainModel

interface TaskDataSource {

    fun getSelectedTask(taskId: Int): Flow<ToDoTaskDataModel?>

    suspend fun addTask(toDoTask: ToDoTaskDataModel)

}