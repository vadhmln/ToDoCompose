package ru.vdh.todocompose.secondfeature.presentation.mapper

import ru.vdh.todocompose.secondfeature.domain.model.ToDoTaskDomainModel
import ru.vdh.todocompose.secondfeature.presentation.model.ToDoTaskPresentationModel

class ToDoTaskPresentationToDomainMapper {

    fun toDomain(input: ToDoTaskPresentationModel) =
        ToDoTaskDomainModel(
            date = input.date,
            id = input.id,
            title = input.title,
            priority = input.priority,
            description = input.description
        )
}