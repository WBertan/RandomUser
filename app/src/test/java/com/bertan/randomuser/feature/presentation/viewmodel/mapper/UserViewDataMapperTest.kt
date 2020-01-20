package com.bertan.randomuser.feature.presentation.viewmodel.mapper

import com.bertan.randomuser.api.domain.data.Gender
import com.bertan.randomuser.api.domain.data.UserData
import com.bertan.randomuser.api.domain.data.UserPicture
import com.bertan.randomuser.feature.presentation.view.data.UserViewData
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserViewDataMapperTest {

    private lateinit var mapper: UserViewDataMapper

    @Before
    fun setup() {
        mapper = UserViewDataMapper()
    }

    private fun defaultUserData(gender: Gender) = UserData(
        gender = gender,
        title = "titleValue",
        firstName = "firstValue",
        lastName = "lastValue",
        email = "emailValue",
        picture = UserPicture(
            large = "largeValue",
            medium = "mediumValue",
            thumbnail = "thumbnailValue"
        )
    )

    private fun defaultUserViewData(backgroundColor: String): UserViewData = UserViewData(
        displayName = "titleValue firstValue lastValue",
        email = "emailValue",
        thumbnail = "thumbnailValue",
        backgroundColor = backgroundColor
    )

    @Test
    fun whenToViewData_thenReturnMappedDataToViewData() {
        mapOf(
            defaultUserData(gender = Gender.MALE) to defaultUserViewData(backgroundColor = "#ffe8d6"),
            defaultUserData(gender = Gender.FEMALE) to defaultUserViewData(backgroundColor = "#ede59a"),
            defaultUserData(gender = mock()) to defaultUserViewData(backgroundColor = "#f0e3ff")
        ).forEach { (dto, expectedResult) ->
            val result = mapper.toViewData(dto)
            Assert.assertEquals(expectedResult, result)
        }
    }

}