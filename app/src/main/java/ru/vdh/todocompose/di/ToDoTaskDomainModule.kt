package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.todocompose.secondfeature.domain.repository.ToDoTaskRepository
import ru.vdh.todocompose.todolist.domain.usecase.AddTaskUseCase
import ru.vdh.todocompose.secondfeature.domain.usecase.GetSelectedTaskUseCase

@Module
@InstallIn(ViewModelComponent::class)
class ToDoTaskDomainModule {

    @Provides
    fun provideGetSelectedTaskUseCase(
        toDoTaskRepository: ToDoTaskRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetSelectedTaskUseCase =
        GetSelectedTaskUseCase(
            toDoTaskRepository = toDoTaskRepository,
        )
}