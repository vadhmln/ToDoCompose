package ru.vdh.todocompose.todolist.domain.usecase

import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository

class GetSelectedTaskUseCase(
    private val toDoListRepository: ToDoListRepository
)  {

    operator fun invoke(taskId: Int) =
        toDoListRepository.getSelectedTask(taskId)
}