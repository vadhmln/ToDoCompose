package ru.vdh.todocompose.secondfeature.domain.usecase

import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.todocompose.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.todocompose.secondfeature.domain.model.NewFeatureDomainModel
import ru.vdh.todocompose.secondfeature.domain.repository.NewFeatureRepository

class GetNewFeatureUseCase(
    private val newFeatureRepository: NewFeatureRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<NewFeatureDomainModel, NewFeatureDomainModel>(coroutineContextProvider) {

    fun execute(): NewFeatureDomainModel {
        return newFeatureRepository.get()
    }

    override fun executeInBackground(request: NewFeatureDomainModel) = newFeatureRepository.get()
}