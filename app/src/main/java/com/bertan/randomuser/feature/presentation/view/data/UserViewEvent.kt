package com.bertan.randomuser.feature.presentation.view.data

sealed class UserViewEvent

data class Error(val error: Throwable) : UserViewEvent()