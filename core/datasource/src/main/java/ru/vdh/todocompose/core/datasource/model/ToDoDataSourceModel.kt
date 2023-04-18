package ru.vdh.todocompose.core.datasource.model

data class ToDoDataSourceModel(
    val id: Int,
    val title: String,
    val priority: String,
    val description: String
)