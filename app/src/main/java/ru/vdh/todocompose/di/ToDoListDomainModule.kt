package ru.vdh.todocompose.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.todocompose.todolist.data.repository.DataStoreRepositoryImpl
import ru.vdh.todocompose.todolist.domain.repository.DataStoreRepository
import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository
import ru.vdh.todocompose.todolist.domain.usecase.AddTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.DeleteAllTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.DeleteTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.GetAllTasksUseCase
import ru.vdh.todocompose.todolist.domain.usecase.GetSelectedTaskUseCase
import ru.vdh.todocompose.todolist.domain.usecase.PersistSortStateUseCase
import ru.vdh.todocompose.todolist.domain.usecase.ReadSortStateUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SearchTasksUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SortByHighPriorityUseCase
import ru.vdh.todocompose.todolist.domain.usecase.SortByLowPriorityUseCase
import ru.vdh.todocompose.todolist.domain.usecase.UpdateTaskUseCase

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
    fun provideSortByLowPriorityUseCase(
        toDoListRepository: ToDoListRepository,
    ): SortByLowPriorityUseCase =
        SortByLowPriorityUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideSortByHighPriorityUseCase(
        toDoListRepository: ToDoListRepository,
    ): SortByHighPriorityUseCase =
        SortByHighPriorityUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideGetSelectedTaskUseCase(
        toDoListRepository: ToDoListRepository,
    ): GetSelectedTaskUseCase =
        GetSelectedTaskUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideAddTaskUseCase(
        toDoListRepository: ToDoListRepository,
    ): AddTaskUseCase =
        AddTaskUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideUpdateTaskUseCase(
        toDoListRepository: ToDoListRepository,
    ): UpdateTaskUseCase =
        UpdateTaskUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideDeleteTaskUseCase(
        toDoListRepository: ToDoListRepository,
    ): DeleteTaskUseCase =
        DeleteTaskUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideSearchTasksUseCase(
        toDoListRepository: ToDoListRepository,
    ): SearchTasksUseCase =
        SearchTasksUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideDeleteAllTaskUseCase(
        toDoListRepository: ToDoListRepository,
    ): DeleteAllTaskUseCase =
        DeleteAllTaskUseCase(
            toDoListRepository = toDoListRepository,
        )

    @Provides
    fun provideReadSortStateUseCase(
        dataStoreRepository: DataStoreRepository,
    ): ReadSortStateUseCase =
        ReadSortStateUseCase(
            dataStoreRepository = dataStoreRepository,
        )

    @Provides
    fun providePersistSortStateUseCase(
        dataStoreRepository: DataStoreRepository,
    ): PersistSortStateUseCase =
        PersistSortStateUseCase(
            dataStoreRepository = dataStoreRepository,
        )

    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepositoryImpl(context = context)
}