package ru.vdh.todocompose.todolist.domain.usecase

import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository

class SortByLowPriorityUseCase(
    private val toDoListRepository: ToDoListRepository
) {

    operator fun invoke() =
        toDoListRepository.sortByLowPriority()

}