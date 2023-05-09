package ru.vdh.todocompose.core.datasource.mapper

import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.core.database.model.PriorityDatabaseModel
import ru.vdh.todocompose.core.database.model.ToDoTaskDatabaseModel

class DataBaseToToDoListDataMapper {

    fun toData(input: ToDoTaskDatabaseModel?) =
        input?.let {
            ToDoTaskDataModel(
                id = input.id,
                title = input.title,
                description = input.description,
                priority = parsePriority(input.priority),
                date = input.date,
            )
        }

    private fun parsePriority(priority: PriorityDatabaseModel): String {
        return when (priority) {
            PriorityDatabaseModel.HIGH -> {
                "HIGH"
            }

            PriorityDatabaseModel.MEDIUM -> {
                "MEDIUM"
            }

            PriorityDatabaseModel.LOW -> {
                "LOW"
            }

            else -> "NONE"
        }
    }


}
