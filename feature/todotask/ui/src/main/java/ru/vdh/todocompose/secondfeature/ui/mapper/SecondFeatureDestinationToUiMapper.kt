package ru.vdh.todocompose.secondfeature.ui.mapper

import ru.vdh.todocompose.core.ui.navigation.mapper.DestinationPresentationToUiMapper
import ru.vdh.todocompose.core.ui.navigation.model.UiDestination

interface SecondFeatureDestinationToUiMapper : DestinationPresentationToUiMapper {

    abstract class NewFeatureUiDestination(
        open val id: Int
    ) : UiDestination
}
