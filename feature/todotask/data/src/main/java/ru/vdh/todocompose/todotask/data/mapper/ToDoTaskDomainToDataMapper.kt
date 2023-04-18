package ru.vdh.todocompose.todotask.data.mapper

import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.secondfeature.domain.model.ToDoTaskDomainModel

class ToDoTaskDomainToDataMapper {

    fun toData(input: ToDoTaskDomainModel) =
        ToDoTaskDataModel(
            date = input.date,
            id = input.id,
            title = input.title,
            priority = input.priority,
            description = input.description
        )
}