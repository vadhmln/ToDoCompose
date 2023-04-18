package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.todocompose.core.data.datasource.ToDoDataSource
import ru.vdh.todocompose.secondfeature.datasource.TaskDataSourceImpl
import ru.vdh.todocompose.todotask.data.datasource.TaskDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ToDoTaskDatasourceModule {

    @Provides
    @Singleton
    fun provideTaskDataSource(
        toDoDataSource: ToDoDataSource,
    ): TaskDataSource = TaskDataSourceImpl(
        toDoDataSource,
    )
}