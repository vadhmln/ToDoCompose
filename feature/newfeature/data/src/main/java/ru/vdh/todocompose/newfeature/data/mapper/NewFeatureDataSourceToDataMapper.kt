package ru.vdh.todocompose.newfeature.data.mapper

import ru.vdh.todocompose.newfeature.data.model.NewFeatureDataModel
import ru.vdh.todocompose.newfeature.domain.model.NewFeatureDomainModel

class NewFeatureDataSourceToDataMapper {

    fun toDataSource(userName: NewFeatureDomainModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}