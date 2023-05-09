package ru.vdh.todocompose.core.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class SharedViewModel @Inject constructor() : ViewModel() {

    var id by mutableStateOf(0)
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var priority by mutableStateOf("LOW")
    var date by mutableStateOf(0L)
}


