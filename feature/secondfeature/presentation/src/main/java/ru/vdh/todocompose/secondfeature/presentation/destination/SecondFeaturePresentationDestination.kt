package ru.vdh.todocompose.secondfeature.presentation.destination

import ru.vdh.todocompose.core.presentation.model.PresentationDestination

sealed interface SecondFeaturePresentationDestination: PresentationDestination {

    data class NewFeature(
        val id: Int
    ) : SecondFeaturePresentationDestination
}