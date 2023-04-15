package ru.vdh.todocompose.secondfeature.data.mapper

import ru.vdh.todocompose.secondfeature.data.model.NewFeatureDataModel
import ru.vdh.todocompose.secondfeature.domain.model.NewFeatureDomainModel

class NewFeatureDataToDataSourceMapper {

    fun toDataSource(userName: NewFeatureDomainModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}