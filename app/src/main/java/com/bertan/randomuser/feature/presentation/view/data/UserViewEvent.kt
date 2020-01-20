package com.bertan.randomuser.feature.presentation.view.data

sealed class UserViewEvent

data class Error(val error: Throwable) : UserViewEvent() {
    val message: String? = error.message
}

data class UserClickedDialog(val title: String, val message: String) : UserViewEvent()