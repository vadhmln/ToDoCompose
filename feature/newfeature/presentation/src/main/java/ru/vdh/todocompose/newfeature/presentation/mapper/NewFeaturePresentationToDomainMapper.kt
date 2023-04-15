package ru.vdh.todocompose.newfeature.presentation.mapper

import ru.vdh.todocompose.newfeature.domain.model.NewFeatureDomainModel
import ru.vdh.todocompose.newfeature.presentation.model.NewFeaturePresentationModel

class NewFeaturePresentationToDomainMapper {

    fun toDomain(newUser: NewFeaturePresentationModel) =
        NewFeatureDomainModel(
            firstName = newUser.firstName,
            lastName = newUser.lastName
        )
}
