package ru.vdh.todocompose.secondfeature.ui.binder

import ru.vdh.todocompose.core.ui.mapper.ViewStateBinder
import ru.vdh.todocompose.secondfeature.presentation.model.NewFeatureViewState
import ru.vdh.todocompose.secondfeature.ui.view.NewFeatureViewsProvider

class NewFeatureViewStateBinder :
    ViewStateBinder<NewFeatureViewState, NewFeatureViewsProvider> {
    override fun NewFeatureViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}