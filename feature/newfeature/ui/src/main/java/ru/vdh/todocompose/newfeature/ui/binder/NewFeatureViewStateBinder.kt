package ru.vdh.todocompose.newfeature.ui.binder

import ru.vdh.todocompose.core.ui.mapper.ViewStateBinder
import ru.vdh.todocompose.newfeature.presentation.model.NewFeatureViewState
import ru.vdh.todocompose.newfeature.ui.view.NewFeatureViewsProvider

class NewFeatureViewStateBinder :
    ViewStateBinder<NewFeatureViewState, NewFeatureViewsProvider> {
    override fun NewFeatureViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}