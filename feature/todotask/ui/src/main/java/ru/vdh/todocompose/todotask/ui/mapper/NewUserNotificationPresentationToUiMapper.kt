package ru.vdh.todocompose.todotask.ui.mapper

import ru.vdh.todocompose.core.ui.mapper.NotificationPresentationToUiMapper
import ru.vdh.todocompose.core.ui.model.NotificationUiModel
import ru.vdh.todocompose.todotask.presentation.model.NewFeaturePresentationNotification

class NewUserNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<NewFeaturePresentationNotification> {
    override fun toUi(
        presentationNotification: NewFeaturePresentationNotification
    ): NotificationUiModel {
        throw IllegalStateException("Notifications not supported.")
    }
}
