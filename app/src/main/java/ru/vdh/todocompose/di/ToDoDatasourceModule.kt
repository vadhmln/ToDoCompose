package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import ru.vdh.todocompose.core.data.datasource.ToDoDataSource
import ru.vdh.todocompose.core.database.dao.ToDoDao
import ru.vdh.todocompose.core.datasource.ToDoDataSourceImpl
import ru.vdh.todocompose.core.datasource.mapper.DataBaseToToDoListDataMapper
import ru.vdh.todocompose.core.datasource.mapper.ToDoDataToDataBaseMapper

@Module
@InstallIn(SingletonComponent::class)
class ToDoDatasourceModule {

    @Provides
    fun providesDataBaseToToDoListDataMapper() = DataBaseToToDoListDataMapper()

    @Provides
    fun providesToDoDataToDataBaseMapper() = ToDoDataToDataBaseMapper()

    @Provides
    @Singleton
    fun provideToDoDataSource(
        dataBaseToToDoListDataMapper: DataBaseToToDoListDataMapper,
        toDoDataToDataBaseMapper: ToDoDataToDataBaseMapper,
        toDoDao: ToDoDao,
    ): ToDoDataSource = ToDoDataSourceImpl(
        dataBaseToToDoListDataMapper,
        toDoDataToDataBaseMapper,
        toDoDao
    )
}