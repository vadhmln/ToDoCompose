package ru.vdh.todocompose.secondfeature.domain.usecase

import ru.vdh.todocompose.secondfeature.domain.repository.ToDoTaskRepository

class GetSelectedTaskUseCase(
    private val toDoTaskRepository: ToDoTaskRepository,
) {

    operator fun invoke(taskId: Int) =
      toDoTaskRepository.getSelectedTask(taskId)

}