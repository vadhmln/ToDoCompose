package ru.vdh.todocompose.secondfeature.datasource.mapper

import ru.vdh.todocompose.secondfeature.data.model.NewFeatureDataModel
import ru.vdh.todocompose.secondfeature.datasource.model.NewFeatureDataSourceModel

class NewFeatureDataToDataSourceMapper {

    fun toData(userName: NewFeatureDataSourceModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}