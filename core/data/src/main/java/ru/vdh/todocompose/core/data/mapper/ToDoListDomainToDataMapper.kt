package ru.vdh.todocompose.core.data.mapper

import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.core.domain.model.ToDoTaskDomainModel

class ToDoListDomainToDataMapper {

    fun toData(input: ToDoTaskDomainModel) =
        ToDoTaskDataModel(
            date = input.date,
            id = input.id,
            title = input.title,
            priority = input.priority,
            description = input.description
        )
}