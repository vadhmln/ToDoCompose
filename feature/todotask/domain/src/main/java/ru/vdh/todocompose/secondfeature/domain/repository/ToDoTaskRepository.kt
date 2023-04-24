package ru.vdh.todocompose.secondfeature.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.secondfeature.domain.model.ToDoTaskDomainModel

interface ToDoTaskRepository {
    fun getSelectedTask(taskId: Int): Flow<ToDoTaskDomainModel?>
}