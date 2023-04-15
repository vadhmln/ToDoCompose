package ru.vdh.todocompose.core.presentation.viewmodel.usecase

import kotlinx.coroutines.CoroutineScope
import ru.vdh.todocompose.core.domain.usecase.UseCaseExecutor

typealias UseCaseExecutorProvider =
    @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor
