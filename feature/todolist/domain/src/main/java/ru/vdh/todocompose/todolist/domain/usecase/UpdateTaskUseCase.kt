package ru.vdh.todocompose.todolist.domain.usecase

import ru.vdh.todocompose.todolist.domain.model.ToDoTaskDomainModel
import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository

class UpdateTaskUseCase(
    private val toDoListRepository: ToDoListRepository,
) {

    suspend operator fun invoke(toDoTask: ToDoTaskDomainModel) =
        toDoListRepository.updateTask(toDoTask)
}