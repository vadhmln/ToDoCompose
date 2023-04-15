package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.todocompose.core.domain.coroutine.CoroutineContextProvider

import ru.vdh.todocompose.secondfeature.domain.repository.NewFeatureRepository
import ru.vdh.todocompose.secondfeature.domain.usecase.GetNewFeatureUseCase
import ru.vdh.todocompose.secondfeature.domain.usecase.SaveNewFeatureUseCase


@Module
@InstallIn(ViewModelComponent::class)
class SecondFeatureDomainModule {

    @Provides
    fun provideGetNewFeatureUseCase(
        newFeatureRepository: NewFeatureRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetNewFeatureUseCase =
        GetNewFeatureUseCase(
            newFeatureRepository = newFeatureRepository,
            coroutineContextProvider = coroutineContextProvider
        )


    @Provides
    fun provideSaveNewFeatureUseCase(
        newFeatureRepository: NewFeatureRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): SaveNewFeatureUseCase =
        SaveNewFeatureUseCase(
            newFeatureRepository = newFeatureRepository,
            coroutineContextProvider = coroutineContextProvider
        )

}