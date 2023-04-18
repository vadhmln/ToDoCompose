package ru.vdh.todocompose.core.domain.model

data class ToDoTaskDomainModel(
    val id: Int,
    val title: String,
    val priority: String,
    val description: String,
    var date: Long?,
)