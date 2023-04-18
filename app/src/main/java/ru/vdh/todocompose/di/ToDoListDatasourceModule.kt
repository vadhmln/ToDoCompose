package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import ru.vdh.todocompose.core.data.datasource.ToDoDataSource
import ru.vdh.todocompose.todolist.data.datasource.ToDoListDataSource
import ru.vdh.todocompose.todolist.datasource.ToDoListDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
class ToDoListDatasourceModule {

    @Provides
    @Singleton
    fun provideNewFeatureRepository(
        toDoDataSource: ToDoDataSource,
    ): ToDoListDataSource = ToDoListDataSourceImpl(
        toDoDataSource
    )
}