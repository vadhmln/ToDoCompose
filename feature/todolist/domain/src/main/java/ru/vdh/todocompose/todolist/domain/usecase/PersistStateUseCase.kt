package ru.vdh.todocompose.todolist.domain.usecase

import ru.vdh.todocompose.todolist.domain.repository.DataStoreRepository

class PersistSortStateUseCase(
    private val dataStoreRepository: DataStoreRepository
) {

     suspend operator fun invoke(priority: String) =
         dataStoreRepository.persistSortState(priority = priority)
}