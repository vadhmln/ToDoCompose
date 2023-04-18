package ru.vdh.todocompose.secondfeature.presentation.model

import androidx.compose.ui.graphics.Color
import ru.vdh.todocompose.core.ui.theme.HighPriorityColor
import ru.vdh.todocompose.core.ui.theme.LowPriorityColor
import ru.vdh.todocompose.core.ui.theme.MediumPriorityColor
import ru.vdh.todocompose.core.ui.theme.NonePriorityColor

enum class PriorityPresentationModel(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}