package ru.vdh.todocompose.core.ui.mapper

import ru.vdh.todocompose.core.ui.model.NotificationUiModel

interface NotificationPresentationToUiMapper<PRESENTATION_NOTIFICATION : Any> {
    fun toUi(presentationNotification: PRESENTATION_NOTIFICATION): NotificationUiModel
}
