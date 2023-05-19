package ru.vdh.todocompose.todolist.domain.usecase

import ru.vdh.todocompose.todolist.domain.repository.DataStoreRepository

class ReadSortStateUseCase(
    private val dataStoreRepository: DataStoreRepository
) {

     operator fun invoke() =
         dataStoreRepository.readSortState()
}