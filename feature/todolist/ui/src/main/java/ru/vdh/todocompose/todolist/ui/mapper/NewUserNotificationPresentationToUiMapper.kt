package ru.vdh.todocompose.todolist.ui.mapper

import ru.vdh.todocompose.core.ui.mapper.NotificationPresentationToUiMapper
import ru.vdh.todocompose.core.ui.model.NotificationUiModel
import ru.vdh.todocompose.todolist.presentation.model.NewFeaturePresentationNotification

class NewUserNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<NewFeaturePresentationNotification> {
    override fun toUi(
        presentationNotification: NewFeaturePresentationNotification
    ): NotificationUiModel {
        throw IllegalStateException("Notifications not supported.")
    }
}
