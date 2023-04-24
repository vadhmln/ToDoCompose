package ru.vdh.todocompose.todolist.presentation.mapper

import ru.vdh.todocompose.todolist.presentation.model.ToDoTaskPresentationModel
import ru.vdh.todocompose.todolist.domain.model.ToDoTaskDomainModel

class ToDoListDomainToPresentationMapper {

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