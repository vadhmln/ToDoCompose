package ru.vdh.todocompose.newfeature.domain.repository

import ru.vdh.todocompose.newfeature.domain.model.NewFeatureDomainModel

interface NewFeatureRepository {

    fun save(newFeature: NewFeatureDomainModel): Boolean

    fun get(): NewFeatureDomainModel
}