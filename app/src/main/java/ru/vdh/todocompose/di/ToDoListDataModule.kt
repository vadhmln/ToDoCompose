package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import ru.vdh.todocompose.todolist.data.datasource.ToDoListDataSource
import ru.vdh.todocompose.todolist.data.mapper.ToDoListDataToDomainMapper
import ru.vdh.todocompose.todolist.data.mapper.ToDoListDomainToDataMapper
import ru.vdh.todocompose.todolist.data.repository.ToDoListRepositoryImpl
import ru.vdh.todocompose.todolist.domain.repository.ToDoListRepository
import ru.vdh.todocompose.todolist.presentation.mapper.ToDoListDomainToPresentationMapper
import ru.vdh.todocompose.todolist.presentation.mapper.ToDoListPresentationToDomainMapper

@Module
@InstallIn(SingletonComponent::class)
class ToDoListDataModule {

    @Provides
    fun providesToDoListDataToDomainMapper() = ToDoListDataToDomainMapper()

    @Provides
    fun providesToDoListDomainToDataMapper() = ToDoListDomainToDataMapper()

    @Provides
    fun providesToDoListDomainToPresentationMapper() = ToDoListDomainToPresentationMapper()

    @Provides
    fun providesToDoListPresentationToDomainMapper() = ToDoListPresentationToDomainMapper()

    @Provides
    @Singleton
    fun provideToDoListRepository(
        toDoListDataSource: ToDoListDataSource,
        toDoListDataToDomainMapper: ToDoListDataToDomainMapper,
        toDoListDomainToDataMapper: ToDoListDomainToDataMapper
    ): ToDoListRepository = ToDoListRepositoryImpl(
        toDoListDataSource = toDoListDataSource,
        toDoListDataToDomainMapper,
        toDoListDomainToDataMapper
    )
}