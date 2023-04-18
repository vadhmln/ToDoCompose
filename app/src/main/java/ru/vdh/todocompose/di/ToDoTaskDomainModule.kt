package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.todocompose.secondfeature.domain.repository.ToDoTaskRepository
import ru.vdh.todocompose.secondfeature.domain.usecase.GetSelectedTaskUseCase
import ru.vdh.todocompose.secondfeature.domain.usecase.SaveNewFeatureUseCase

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

    @Provides
    fun provideSaveNewFeatureUseCase(
        toDoTaskRepository: ToDoTaskRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): SaveNewFeatureUseCase =
        SaveNewFeatureUseCase(
            toDoTaskRepository = toDoTaskRepository,
            coroutineContextProvider = coroutineContextProvider
        )
}