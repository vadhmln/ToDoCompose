package ru.vdh.todocompose.todotask.presentation.model

data class ToDoTaskPresentationModel(
    val id: Int,
    val title: String,
    val priority: String,
    val description: String,
    var date: Long?,
)