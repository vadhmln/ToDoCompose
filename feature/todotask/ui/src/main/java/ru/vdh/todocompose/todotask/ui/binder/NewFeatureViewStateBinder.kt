package ru.vdh.todocompose.todotask.ui.binder

import ru.vdh.todocompose.core.ui.mapper.ViewStateBinder
import ru.vdh.todocompose.todotask.presentation.model.NewFeatureViewState
import ru.vdh.todocompose.todotask.ui.view.NewFeatureViewsProvider

class NewFeatureViewStateBinder :
    ViewStateBinder<NewFeatureViewState, NewFeatureViewsProvider> {
    override fun NewFeatureViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}