package ru.vdh.todocompose.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vdh.todocompose.secondfeature.data.datasource.NewFeatureDataSource
import ru.vdh.todocompose.secondfeature.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.todocompose.secondfeature.data.mapper.NewFeatureDataToDomainMapper
import ru.vdh.todocompose.secondfeature.data.repository.NewFeatureRepositoryImpl
import ru.vdh.todocompose.secondfeature.datasource.SharedPrefNewFeatureDataSource
import ru.vdh.todocompose.secondfeature.domain.repository.NewFeatureRepository
import ru.vdh.todocompose.secondfeature.presentation.mapper.NewFeatureDomainToPresentationMapper
import ru.vdh.todocompose.secondfeature.presentation.mapper.NewFeaturePresentationToDomainMapper

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SecondFeatureDataModule {

    @Provides
    fun providesNewFeatureDataToDomainMapper() = NewFeatureDataToDomainMapper()

    @Provides
    fun providesNewFeatureDataModelToDataSourceMapper() = NewFeatureDataToDataSourceMapper()

    @Provides
    fun providesNewFeaturePresentationToDomainMapper() = NewFeaturePresentationToDomainMapper()

    @Provides
    fun providesNewFeatureDomainToPresentationMapper() = NewFeatureDomainToPresentationMapper()

    @Provides
    @Singleton
    fun provideNewFeatureDataSource(@ApplicationContext context: Context): NewFeatureDataSource {
        return SharedPrefNewFeatureDataSource(context = context)
    }

    @Provides
    @Singleton
    fun provideNewFeatureRepository(
        newFeatureDataSource: NewFeatureDataSource,
        newFeatureDataToDomainMapper: NewFeatureDataToDomainMapper,
        newFeatureDataToDataSourceMapper: NewFeatureDataToDataSourceMapper
    ): NewFeatureRepository = NewFeatureRepositoryImpl(
        newFeatureDataSource = newFeatureDataSource,
        newFeatureDataToDomainMapper,
        newFeatureDataToDataSourceMapper
    )
}