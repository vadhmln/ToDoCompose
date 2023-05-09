package ru.vdh.todocompose.todolist.data.mapper

import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.todolist.domain.model.ToDoTaskDomainModel

class ToDoListDomainToDataMapper {

    fun toData(input: ToDoTaskDomainModel) =
        ToDoTaskDataModel(
            id = input.id,
            title = input.title,
            priority = input.priority,
            description = input.description,
            date = input.date,
        )
}