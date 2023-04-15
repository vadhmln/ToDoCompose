package ru.vdh.todocompose.secondfeature.presentation.mapper

import ru.vdh.todocompose.secondfeature.domain.model.NewFeatureDomainModel
import ru.vdh.todocompose.secondfeature.presentation.model.NewFeaturePresentationModel

class NewFeaturePresentationToDomainMapper {

    fun toDomain(newUser: NewFeaturePresentationModel) =
        NewFeatureDomainModel(
            firstName = newUser.firstName,
            lastName = newUser.lastName
        )
}
