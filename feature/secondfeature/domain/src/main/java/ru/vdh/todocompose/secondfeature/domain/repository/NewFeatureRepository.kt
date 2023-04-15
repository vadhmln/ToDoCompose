package ru.vdh.todocompose.secondfeature.domain.repository

import ru.vdh.todocompose.secondfeature.domain.model.NewFeatureDomainModel

interface NewFeatureRepository {

    fun save(newFeature: NewFeatureDomainModel): Boolean

    fun get(): NewFeatureDomainModel
}