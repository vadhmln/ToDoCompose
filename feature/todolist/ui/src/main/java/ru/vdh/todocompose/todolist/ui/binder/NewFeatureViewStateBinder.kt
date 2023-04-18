package ru.vdh.todocompose.todolist.ui.binder

import ru.vdh.todocompose.core.ui.mapper.ViewStateBinder
import ru.vdh.todocompose.todolist.presentation.model.NewFeatureViewState
import ru.vdh.todocompose.todolist.ui.view.NewFeatureViewsProvider

class NewFeatureViewStateBinder :
    ViewStateBinder<NewFeatureViewState, NewFeatureViewsProvider> {
    override fun NewFeatureViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}