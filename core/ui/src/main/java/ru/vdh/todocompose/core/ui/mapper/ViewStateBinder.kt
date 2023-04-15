package ru.vdh.todocompose.core.ui.mapper

import ru.vdh.todocompose.core.ui.view.ViewsProvider

interface ViewStateBinder<in VIEW_STATE : Any, in VIEWS_PROVIDER : ViewsProvider> {
    fun VIEWS_PROVIDER.bindState(viewState: VIEW_STATE)
}
