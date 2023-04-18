package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository
import ru.vdh.todocompose.todolist.domain.usecase.GetAllTasksUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SaveNewFeatureUseCase

@Module
@InstallIn(ViewModelComponent::class)
class ToDoListDomainModule {

    @Provides
    fun provideGetAllTasksUseCase(
        toDoListRepository: ToDoListRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetAllTasksUseCase =
        GetAllTasksUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideSaveNewFeatureUseCase(
        toDoListRepository: ToDoListRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): SaveNewFeatureUseCase =
        SaveNewFeatureUseCase(
            toDoListRepository = toDoListRepository,
            coroutineContextProvider = coroutineContextProvider
        )
}