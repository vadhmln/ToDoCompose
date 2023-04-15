package ru.vdh.todocompose.newfeature.data.datasource

import ru.vdh.todocompose.newfeature.data.model.NewFeatureDataModel

interface NewFeatureDataSource {
    fun save(newFeatureDataModel: NewFeatureDataModel): Boolean

    fun get(): NewFeatureDataModel
}