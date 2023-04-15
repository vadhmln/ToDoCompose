package ru.vdh.todocompose.core.ui.navigation.mapper

import ru.vdh.todocompose.core.presentation.model.PresentationDestination
import ru.vdh.todocompose.core.ui.navigation.model.UiDestination

interface DestinationPresentationToUiMapper {
    fun toUi(input: PresentationDestination): UiDestination
}
