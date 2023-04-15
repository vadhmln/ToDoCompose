package ru.vdh.todocompose.secondfeature.presentation.mapper

import ru.vdh.todocompose.secondfeature.domain.model.NewFeatureDomainModel
import ru.vdh.todocompose.secondfeature.presentation.model.NewFeaturePresentationModel

class NewFeatureDomainToPresentationMapper {

    fun toPresentation(user: NewFeatureDomainModel) =
        NewFeaturePresentationModel(
            firstName = user.firstName,
            lastName = user.lastName
        )
}