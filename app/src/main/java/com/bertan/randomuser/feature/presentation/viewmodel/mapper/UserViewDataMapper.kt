package com.bertan.randomuser.feature.presentation.viewmodel.mapper

import com.bertan.randomuser.api.domain.data.Gender
import com.bertan.randomuser.api.domain.data.UserData
import com.bertan.randomuser.feature.presentation.view.data.UserViewData

class UserViewDataMapper {

    companion object {
        private const val defaultColor = "#f0e3ff"
        private val genderToColor = mapOf(
            Gender.MALE to "#ffe8d6",
            Gender.FEMALE to "#ede59a"
        )
    }

    fun toViewData(data: UserData): UserViewData = UserViewData(
        displayName = data.run { "$title $firstName $lastName" },
        email = data.email,
        thumbnail = data.picture.thumbnail,
        backgroundColor = genderToColor.getOrElse(data.gender) { defaultColor }
    )

}