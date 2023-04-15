package ru.vdh.todocompose.newfeature.presentation.mapper

import ru.vdh.todocompose.newfeature.domain.model.NewFeatureDomainModel
import ru.vdh.todocompose.newfeature.presentation.model.NewFeaturePresentationModel

class NewFeatureDomainToPresentationMapper {

    fun toPresentation(user: NewFeatureDomainModel) =
        NewFeaturePresentationModel(
            firstName = user.firstName,
            lastName = user.lastName
        )
}