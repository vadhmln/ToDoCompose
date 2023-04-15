package ru.vdh.todocompose.secondfeature.presentation.model

data class NewFeatureViewState(
    val saveResult: Boolean = false,
    val firstName: String = "",
    val lastName: String = ""
)