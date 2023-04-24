package ru.vdh.todocompose.todotask.data.mapper

import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.secondfeature.domain.model.ToDoTaskDomainModel

class ToDoTaskDataToDomainMapper {

    fun toDomain(input: ToDoTaskDataModel?) =
        input?.let {
            ToDoTaskDomainModel(
                date = input.date,
                id = input.id,
                title = input.title,
                priority = input.priority,
                description = input.description
            )
        }
}