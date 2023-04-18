package ru.vdh.todocompose.secondfeature.domain.usecase

import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.todocompose.secondfeature.domain.repository.ToDoTaskRepository

class SaveNewFeatureUseCase(
    private val toDoTaskRepository: ToDoTaskRepository,
    private val coroutineContextProvider: CoroutineContextProvider
)  {


}



