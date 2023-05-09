package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.todocompose.todotask.data.datasource.TaskDataSource
import ru.vdh.todocompose.todotask.data.mapper.ToDoTaskDomainToDataMapper
import ru.vdh.todocompose.todotask.data.mapper.ToDoTaskDataToDomainMapper
import ru.vdh.todocompose.todotask.data.repository.ToDoTaskRepositoryImpl
import ru.vdh.todocompose.secondfeature.domain.repository.ToDoTaskRepository
import ru.vdh.todocompose.todotask.presentation.mapper.ToDoTaskDomainToPresentationMapper
import ru.vdh.todocompose.todotask.presentation.mapper.ToDoTaskPresentationToDomainMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ToDoTaskDataModule {

    @Provides
    fun providesToDoTaskDataToDomainMapper() = ToDoTaskDataToDomainMapper()

    @Provides
    fun providesToDoTaskDomainToDataMapper() = ToDoTaskDomainToDataMapper()

    @Provides
    fun providesToDoTaskDomainToPresentationMapper() = ToDoTaskDomainToPresentationMapper()

    @Provides
    fun providesToDoTaskPresentationToDomainMapper() = ToDoTaskPresentationToDomainMapper()

    @Provides
    @Singleton
    fun provideToDoTaskRepository(
        taskDataSource: TaskDataSource,
        toDoTaskDataToDomainMapper: ToDoTaskDataToDomainMapper,
        toDoTaskDomainToDataMapper: ToDoTaskDomainToDataMapper
    ): ToDoTaskRepository = ToDoTaskRepositoryImpl(
        taskDataSource = taskDataSource,
        toDoTaskDataToDomainMapper,
        toDoTaskDomainToDataMapper
    )
}