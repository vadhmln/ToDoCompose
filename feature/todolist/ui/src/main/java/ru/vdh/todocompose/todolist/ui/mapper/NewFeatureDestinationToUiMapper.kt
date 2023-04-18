package ru.vdh.todocompose.todolist.ui.mapper

import ru.vdh.todocompose.core.ui.navigation.mapper.DestinationPresentationToUiMapper
import ru.vdh.todocompose.core.ui.navigation.model.UiDestination

interface NewFeatureDestinationToUiMapper : DestinationPresentationToUiMapper {

    abstract class SecondFeatureUiDestination(
        open val id: Int
    ) : UiDestination
}
