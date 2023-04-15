package ru.vdh.todocompose.core.presentation.viewmodel.coroutine

import kotlinx.coroutines.Dispatchers
import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider

class AppCoroutineContextProvider : CoroutineContextProvider {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
}
