package ru.vdh.todocompose.newfeature.data.mapper

import ru.vdh.todocompose.newfeature.data.model.NewFeatureDataModel
import ru.vdh.todocompose.newfeature.domain.model.NewFeatureDomainModel

class NewFeatureDataToDomainMapper {

    fun toDomain(newFeatureDataModel: NewFeatureDataModel) =
        NewFeatureDomainModel(
            firstName = newFeatureDataModel.firstName,
            lastName = newFeatureDataModel.lastName
        )
}