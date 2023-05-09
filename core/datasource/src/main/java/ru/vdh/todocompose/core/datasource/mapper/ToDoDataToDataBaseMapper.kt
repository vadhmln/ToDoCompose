package ru.vdh.todocompose.core.datasource.mapper

import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.core.database.model.PriorityDatabaseModel
import ru.vdh.todocompose.core.database.model.ToDoTaskDatabaseModel

class ToDoDataToDataBaseMapper() {

    fun toDataBase(input: ToDoTaskDataModel) =
        ToDoTaskDatabaseModel(
            id = input.id,
            title = input.title,
            description = input.description,
            priority = parsePriority(input.priority),
            date = input.date,
        )

    private fun parsePriority(priority: String) =
        when (priority) {
            "HIGH" -> {
                PriorityDatabaseModel.HIGH
            }

            "MEDIUM" -> {
                PriorityDatabaseModel.MEDIUM
            }

            "LOW" -> {
                PriorityDatabaseModel.LOW
            }

            else -> PriorityDatabaseModel.NONE
        }
}
