package com.bertan.randomuser.api.domain.data

data class UserData(
    val gender: Gender,
    val title: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val picture: UserPicture
)

enum class Gender {
    MALE,
    FEMALE
}

data class UserPicture(
    val large: String,
    val medium: String,
    val thumbnail: String
)