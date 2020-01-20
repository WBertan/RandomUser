package com.bertan.randomuser.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bertan.randomuser.api.domain.GetRandomUsersUseCase
import com.bertan.randomuser.feature.presentation.viewmodel.mapper.UserViewDataMapper

@Suppress("UNCHECKED_CAST")
class UserListViewModelFactory(
    private val getRandomUsersUseCase: GetRandomUsersUseCase,
    private val userViewDataMapper: UserViewDataMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        UserListViewModel::class.java -> UserListViewModel(
            getRandomUsersUseCase,
            userViewDataMapper
        )
        else -> error("class not supported!")
    } as T

}