package ru.vdh.todocompose.todolist.datasource.mapper

import ru.vdh.todocompose.core.data.model.ToDoTaskDataModel
import ru.vdh.todocompose.core.database.model.PriorityDatabaseModel
import ru.vdh.todocompose.core.database.model.ToDoTaskDatabaseModel

class ToDoListDataToDataBaseMapper() {

    fun toDataBase(input: ToDoTaskDataModel) =
        ToDoTaskDatabaseModel(
            input.id,
            input.title,
            input.priority,
            parsePriority(input.description),
            input.date,
        )

    private fun parsePriority(priority: String) =
        when (priority) {
            "High priority" -> {
                PriorityDatabaseModel.HIGH
            }

            "Medium priority" -> {
                PriorityDatabaseModel.MEDIUM
            }

            "Low priority" -> {
                PriorityDatabaseModel.LOW
            }

            else -> PriorityDatabaseModel.NONE
        }
}
