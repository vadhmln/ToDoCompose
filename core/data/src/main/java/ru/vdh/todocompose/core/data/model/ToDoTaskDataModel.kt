package ru.vdh.todocompose.core.data.model

data class ToDoTaskDataModel(
    val id: Int,
    val title: String,
    val priority: String,
    val description: String,
    var date: Long?,
)