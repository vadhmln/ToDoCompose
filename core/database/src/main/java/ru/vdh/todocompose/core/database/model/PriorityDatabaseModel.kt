package ru.vdh.todocompose.core.database.model

import androidx.compose.ui.graphics.Color
import ru.vdh.todocompose.core.ui.theme.HighPriorityColor
import ru.vdh.todocompose.core.ui.theme.LowPriorityColor
import ru.vdh.todocompose.core.ui.theme.MediumPriorityColor
import ru.vdh.todocompose.core.ui.theme.NonePriorityColor

enum class PriorityDatabaseModel(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}