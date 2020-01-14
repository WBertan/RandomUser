package com.bertan.randomuser.api.domain.mapper

import com.bertan.randomuser.api.domain.data.Gender
import com.bertan.randomuser.api.domain.data.UserData
import com.bertan.randomuser.api.domain.data.UserPicture
import com.bertan.randomuser.api.repository.model.GenderDto
import com.bertan.randomuser.api.repository.model.UserDto
import com.bertan.randomuser.api.repository.model.UserNameDto
import com.bertan.randomuser.api.repository.model.UserPictureDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserDataMapperTest {

    private lateinit var mapper: UserDataMapper

    @Before
    fun setup() {
        mapper = UserDataMapper()
    }

    private fun defaultUserDto(gender: GenderDto): UserDto = UserDto(
        gender = gender,
        name = UserNameDto(
            title = "titleValue",
            first = "firstValue",
            last = "lastValue"
        ),
        email = "emailValue",
        picture = UserPictureDto(
            large = "largeValue",
            medium = "mediumValue",
            thumbnail = "thumbnailValue"
        )
    )

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

    @Test
    fun whenToData_thenReturnMappedDtoToData() {
        mapOf(
            defaultUserDto(gender = GenderDto.MALE) to defaultUserData(gender = Gender.MALE),
            defaultUserDto(gender = GenderDto.FEMALE) to defaultUserData(gender = Gender.FEMALE)
        ).forEach { (dto, expectedResult) ->
            val result = mapper.toData(dto)
            assertEquals(expectedResult, result)
        }
    }

}