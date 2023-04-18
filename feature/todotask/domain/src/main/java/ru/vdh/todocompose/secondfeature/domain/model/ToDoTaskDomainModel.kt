package ru.vdh.todocompose.secondfeature.domain.model

data class ToDoTaskDomainModel(
    val id: Int,
    val title: String,
    val priority: String,
    val description: String,
    var date: Long?,
)