package ru.vdh.todocompose.secondfeature.data.mapper

import ru.vdh.todocompose.secondfeature.data.model.NewFeatureDataModel
import ru.vdh.todocompose.secondfeature.domain.model.NewFeatureDomainModel

class NewFeatureDataToDomainMapper {

    fun toDomain(newFeatureDataModel: NewFeatureDataModel) =
        NewFeatureDomainModel(
            firstName = newFeatureDataModel.firstName,
            lastName = newFeatureDataModel.lastName
        )
}