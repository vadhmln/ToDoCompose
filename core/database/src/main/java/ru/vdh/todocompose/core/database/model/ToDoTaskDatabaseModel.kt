package ru.vdh.todocompose.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vdh.todocompose.common.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoTaskDatabaseModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: PriorityDatabaseModel,
    var date: Long?,
)