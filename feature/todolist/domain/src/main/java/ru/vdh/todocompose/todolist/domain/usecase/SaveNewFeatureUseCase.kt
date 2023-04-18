package ru.vdh.todocompose.todolist.domain.usecase

import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.todocompose.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.todocompose.todolist.domain.model.NewFeatureDomainModel
import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository

class SaveNewFeatureUseCase(
    private val toDoListRepository: ToDoListRepository,
    private val coroutineContextProvider: CoroutineContextProvider
)  {

}



