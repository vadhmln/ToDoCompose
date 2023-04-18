package ru.vdh.todocompose.todolist.presentation.model

sealed class ToDoListState<out T> {
    object Idle : ToDoListState<Nothing>()
    object Loading : ToDoListState<Nothing>()
    data class Success<T>(val data: T) : ToDoListState<T>()
    data class Error(val error: Throwable) : ToDoListState<Nothing>()
}
