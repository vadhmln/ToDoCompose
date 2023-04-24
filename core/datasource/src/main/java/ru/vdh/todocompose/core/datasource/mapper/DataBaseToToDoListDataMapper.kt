package ru.vdh.todocompose.core.datasource.mapper

import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.core.database.model.PriorityDatabaseModel
import ru.vdh.todocompose.core.database.model.ToDoTaskDatabaseModel

class DataBaseToToDoListDataMapper {

    fun toData(input: ToDoTaskDatabaseModel?) =
        input?.let {
            ToDoTaskDataModel(
                input.id,
                input.title,
                parsePriority(input.priority),
                input.description,
                input.date,
            )
        }


    private fun parsePriority(priority: PriorityDatabaseModel): String {
        return when (priority) {
            PriorityDatabaseModel.HIGH -> {
                "High priority"
            }

            PriorityDatabaseModel.MEDIUM -> {
                "Medium priority"
            }

            PriorityDatabaseModel.LOW -> {
                "Low priority"
            }
            PriorityDatabaseModel.NONE -> {
                "None"
            }

            else -> "Low priority"
        }
    }


}
