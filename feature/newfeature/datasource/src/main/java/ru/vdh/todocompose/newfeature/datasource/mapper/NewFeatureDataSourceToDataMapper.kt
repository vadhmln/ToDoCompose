package ru.vdh.todocompose.newfeature.datasource.mapper

import ru.vdh.todocompose.newfeature.data.model.NewFeatureDataModel

class NewFeatureDataSourceToDataMapper {

    fun toDataSource(input: NewFeatureDataModel) =
        NewFeatureDataModel(firstName = input.firstName, lastName = "")
}