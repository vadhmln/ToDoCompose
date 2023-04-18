package ru.vdh.todocompose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vdh.todocompose.core.database.dao.ToDoDao
import ru.vdh.todocompose.core.database.model.ToDoTaskDatabaseModel

@Database(entities = [ToDoTaskDatabaseModel::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao
}