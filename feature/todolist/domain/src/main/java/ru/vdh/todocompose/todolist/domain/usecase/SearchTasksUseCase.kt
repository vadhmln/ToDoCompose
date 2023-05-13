package ru.vdh.todocompose.todolist.domain.usecase

import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository

class SearchTasksUseCase(
    private val toDoListRepository: ToDoListRepository
)  {

    operator fun invoke(searchQuery: String) =
        toDoListRepository.searchDatabase(searchQuery)
}