package ru.vdh.todocompose.secondfeature.data.repository

import ru.vdh.todocompose.secondfeature.data.datasource.NewFeatureDataSource
import ru.vdh.todocompose.secondfeature.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.todocompose.secondfeature.data.mapper.NewFeatureDataToDomainMapper
import ru.vdh.todocompose.secondfeature.domain.model.NewFeatureDomainModel
import ru.vdh.todocompose.secondfeature.domain.repository.NewFeatureRepository

class NewFeatureRepositoryImpl(
    private val newFeatureDataSource: NewFeatureDataSource,
    private val newFeatureDataToDomainMapper: NewFeatureDataToDomainMapper,
    private val newFeatureDataToDataSourceMapper: NewFeatureDataToDataSourceMapper
) : NewFeatureRepository {

    override fun save(newFeature: NewFeatureDomainModel): Boolean {
        val user = newFeatureDataToDataSourceMapper.toDataSource(newFeature)
        return newFeatureDataSource.save(user)
    }

    override fun get(): NewFeatureDomainModel {
        val user = newFeatureDataSource.get()
        return newFeatureDataToDomainMapper.toDomain(user)
    }
}


