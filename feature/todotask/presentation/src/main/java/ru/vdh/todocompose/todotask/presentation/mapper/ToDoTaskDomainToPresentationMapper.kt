package ru.vdh.todocompose.todotask.presentation.mapper

import ru.vdh.todocompose.secondfeature.domain.model.ToDoTaskDomainModel
import ru.vdh.todocompose.todotask.presentation.model.ToDoTaskPresentationModel

class ToDoTaskDomainToPresentationMapper {

    fun toPresentation(input: ToDoTaskDomainModel?) =
        input?.let {
            ToDoTaskPresentationModel(
                date = input.date,
                id = input.id,
                title = input.title,
                priority = input.priority,
                description = input.description
            )
        }
}