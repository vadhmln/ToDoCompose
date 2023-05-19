package ru.vdh.todocompose.todolist.domain.usecase

import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository

class DeleteAllTaskUseCase(
    private val toDoListRepository: ToDoListRepository,
) {

    suspend operator fun invoke() =
        toDoListRepository.deleteAllTasks()
}