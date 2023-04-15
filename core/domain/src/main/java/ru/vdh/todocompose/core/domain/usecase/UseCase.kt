package ru.vdh.todocompose.core.domain.usecase

interface UseCase<REQUEST, RESULT> {
    suspend fun execute(input: REQUEST, onResult: (RESULT) -> Unit)
}
