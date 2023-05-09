package ru.vdh.todocompose.todolist.presentation.mapper

import ru.vdh.todocompose.todolist.domain.model.ToDoTaskDomainModel
import ru.vdh.todocompose.todolist.presentation.model.ToDoTaskPresentationModel

class ToDoListPresentationToDomainMapper {

    fun toDomain(input: ToDoTaskPresentationModel) =
        ToDoTaskDomainModel(
            id = input.id,
            title = input.title,
            priority = input.priority,
            description = input.description,
            date = input.date,
        )
}