package ru.vdh.todocompose.core.domain.usecase

import ru.vdh.todocompose.core.domain.exception.DomainException
import ru.vdh.todocompose.core.domain.exception.UnknownDomainException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class UseCaseExecutor(
    private val coroutineScope: CoroutineScope
) {
    fun <OUTPUT> execute(
        useCase: UseCase<Unit, OUTPUT>,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        execute(useCase, Unit, onSuccess, onException)
    }

    fun <INPUT, OUTPUT> execute(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        onSuccess: (OUTPUT) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) {
        coroutineScope.launch {
            try {
                useCase.execute(value, onSuccess)
            } catch (ignore: CancellationException) {
            } catch (throwable: Throwable) {
                onException(
                    (throwable as? DomainException)
                        ?: UnknownDomainException(throwable)
                )
            }
        }
    }
}
