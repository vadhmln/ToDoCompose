package ru.vdh.todocompose.newfeature.datasource.mapper

import ru.vdh.todocompose.newfeature.data.model.NewFeatureDataModel
import ru.vdh.todocompose.newfeature.datasource.model.NewFeatureDataSourceModel

class NewFeatureDataToDataSourceMapper {

    fun toData(userName: NewFeatureDataSourceModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}