package ru.vdh.todocompose.newfeature.presentation.destination

import ru.vdh.todocompose.core.presentation.model.PresentationDestination

sealed interface NewFeaturePresentationDestination: PresentationDestination {

    data class SecondFeature(
        val id: Int
    ) : NewFeaturePresentationDestination
}