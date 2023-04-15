package ru.vdh.todocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import ru.vdh.todocompose.core.ui.mapper.ViewStateBinder
import ru.vdh.todocompose.core.ui.view.ViewsProvider
import ru.vdh.todocompose.secondfeature.presentation.model.NewFeatureViewState
import ru.vdh.todocompose.secondfeature.ui.binder.NewFeatureViewStateBinder
import ru.vdh.todocompose.secondfeature.ui.mapper.NewUserNotificationPresentationToUiMapper

@Module
@InstallIn(FragmentComponent::class)
class SecondFeatureUiModule {

    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesUserDetailsViewStateBinder() = NewFeatureViewStateBinder()
            as ViewStateBinder<NewFeatureViewState, ViewsProvider>

    @Provides
    fun providesNewUserNotificationPresentationToUiMapper() =
        NewUserNotificationPresentationToUiMapper()
}