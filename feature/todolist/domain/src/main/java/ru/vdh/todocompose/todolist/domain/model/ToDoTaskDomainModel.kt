package ru.vdh.todocompose.todolist.domain.model

data class ToDoTaskDomainModel(
    val id: Int,
    val title: String,
    val priority: String,
    val description: String,
    var date: Long?,
)