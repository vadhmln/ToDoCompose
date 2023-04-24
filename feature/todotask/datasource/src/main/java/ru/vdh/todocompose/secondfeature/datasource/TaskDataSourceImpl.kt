package ru.vdh.todocompose.secondfeature.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.core.data.datasource.ToDoDataSource
import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.todotask.data.datasource.TaskDataSource
import ru.vdh.todocompose.todotask.data.model.NewFeatureDataModel

class TaskDataSourceImpl(
    private val toDoDataSource: ToDoDataSource,
) : TaskDataSource {

    override fun getSelectedTask(taskId: Int): Flow<ToDoTaskDataModel?> =
        toDoDataSource.getSelectedTask(taskId)
}