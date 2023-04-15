package ru.vdh.todocompose.newfeature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vdh.todocompose.core.presentation.viewmodel.BaseViewModel
import ru.vdh.todocompose.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.todocompose.newfeature.domain.usecase.GetNewFeatureUseCase
import ru.vdh.todocompose.newfeature.domain.usecase.SaveNewFeatureUseCase
import ru.vdh.todocompose.newfeature.presentation.destination.NewFeaturePresentationDestination.SecondFeature
import ru.vdh.todocompose.newfeature.presentation.mapper.NewFeatureDomainToPresentationMapper
import ru.vdh.todocompose.newfeature.presentation.mapper.NewFeaturePresentationToDomainMapper
import ru.vdh.todocompose.newfeature.presentation.model.NewFeaturePresentationModel
import ru.vdh.todocompose.newfeature.presentation.model.NewFeaturePresentationNotification
import ru.vdh.todocompose.newfeature.presentation.model.NewFeatureViewState
import javax.inject.Inject

@HiltViewModel
class NewFeatureViewModel @Inject constructor(
    private val getNewFeatureUseCase: GetNewFeatureUseCase,
    private val saveNewFeatureUseCase: SaveNewFeatureUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val newFeaturePresentationToDomainMapper: NewFeaturePresentationToDomainMapper,
    private val newFeatureDomainToPresentationMapper: NewFeatureDomainToPresentationMapper
) : BaseViewModel<NewFeatureViewState, NewFeaturePresentationNotification>(useCaseExecutorProvider) {

    override fun initialState() = NewFeatureViewState()

    private val resultMutableLiveData = MutableLiveData<String>()
    val resultLiveData: LiveData<String> = resultMutableLiveData

    init {
        Log.e("AAA", "UserDetailsViewModel created!!!")
    }

    //вызывается когда связанная с ней активити/fragment уничтожается
    override fun onCleared() {
        Log.e("AAA", "UserDetailsViewModel cleared!!!")
        super.onCleared()
    }

    fun save(newFeaturePresentationModel: NewFeaturePresentationModel) {
        val domainNewUser =
            newFeaturePresentationToDomainMapper.toDomain(newFeaturePresentationModel)
        val result = execute(saveNewFeatureUseCase, domainNewUser)
        resultMutableLiveData.value = "Save result = $result"
    }

    fun load() {
        val userName = getNewFeatureUseCase.execute()
//        execute(getUserNameUseCase, userName)
        resultMutableLiveData.value = "${userName.firstName} ${userName.lastName}"
    }

    fun onSecondFeatureAction(id: Int) {
        navigateTo(SecondFeature(id))
    }
}