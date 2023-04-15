package ru.vdh.todocompose.secondfeature.datasource.mapper

import ru.vdh.todocompose.secondfeature.data.model.NewFeatureDataModel

class NewFeatureDataSourceToDataMapper {

    fun toDataSource(input: NewFeatureDataModel) =
        NewFeatureDataModel(firstName = input.firstName, lastName = "")
}