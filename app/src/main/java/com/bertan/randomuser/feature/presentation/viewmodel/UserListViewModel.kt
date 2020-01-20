package com.bertan.randomuser.feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bertan.randomuser.api.domain.GetRandomUsersUseCase
import com.bertan.randomuser.feature.presentation.view.data.Error
import com.bertan.randomuser.feature.presentation.view.data.UserClickedDialog
import com.bertan.randomuser.feature.presentation.view.data.UserViewData
import com.bertan.randomuser.feature.presentation.view.data.UserViewEvent
import com.bertan.randomuser.feature.presentation.viewmodel.mapper.UserViewDataMapper
import io.reactivex.disposables.CompositeDisposable

class UserListViewModel(
    private val getRandomUsersUseCase: GetRandomUsersUseCase,
    private val userViewDataMapper: UserViewDataMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val internalViewData = MutableLiveData<List<UserViewData>>()
    val viewData: LiveData<List<UserViewData>>
        get() = internalViewData

    private val internalEvents = MutableLiveData<UserViewEvent>()
    val events: LiveData<UserViewEvent>
        get() = internalEvents

    fun start() {
        getRandomUsersUseCase.execute()
            .map { it.map(userViewDataMapper::toViewData) }
            .subscribe(internalViewData::postValue) { internalEvents.postValue(Error(it)) }
            .let(compositeDisposable::add)
    }

    fun onItemClick(item: UserViewData) {
        internalEvents.postValue(
            UserClickedDialog(
                title = item.displayName,
                message = "The user email is:\n${item.email}"
            )
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}