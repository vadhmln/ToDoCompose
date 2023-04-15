package ru.vdh.todocompose.secondfeature.data.datasource

import ru.vdh.todocompose.secondfeature.data.model.NewFeatureDataModel

interface NewFeatureDataSource {
    fun save(newFeatureDataModel: NewFeatureDataModel): Boolean

    fun get(): NewFeatureDataModel
}