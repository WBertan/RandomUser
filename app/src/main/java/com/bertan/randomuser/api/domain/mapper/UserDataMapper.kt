package com.bertan.randomuser.api.domain.mapper

import com.bertan.randomuser.api.domain.data.Gender
import com.bertan.randomuser.api.domain.data.UserData
import com.bertan.randomuser.api.domain.data.UserPicture
import com.bertan.randomuser.api.repository.model.GenderDto
import com.bertan.randomuser.api.repository.model.UserDto
import com.bertan.randomuser.api.repository.model.UserPictureDto

class UserDataMapper {

    fun toData(dto: UserDto): UserData = UserData(
        gender = dto.gender.toData(),
        title = dto.name.title,
        firstName = dto.name.first,
        lastName = dto.name.last,
        email = dto.email,
        picture = dto.picture.toData()
    )

    private fun GenderDto.toData(): Gender = when (this) {
        GenderDto.MALE -> Gender.MALE
        GenderDto.FEMALE -> Gender.FEMALE
    }

    private fun UserPictureDto.toData(): UserPicture = UserPicture(
        large = large,
        medium = medium,
        thumbnail = thumbnail
    )

}