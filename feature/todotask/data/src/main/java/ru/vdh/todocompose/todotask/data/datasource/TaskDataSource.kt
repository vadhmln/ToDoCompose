package ru.vdh.todocompose.todotask.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel

interface TaskDataSource {

    fun getSelectedTask(taskId: Int): Flow<ToDoTaskDataModel?>

}