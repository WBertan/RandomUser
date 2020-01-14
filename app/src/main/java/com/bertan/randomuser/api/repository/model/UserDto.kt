package com.bertan.randomuser.api.repository.model

import com.google.gson.annotations.SerializedName

data class UserResultsDto(
    @SerializedName("results") val users: List<UserDto>
)

data class UserDto(
    @SerializedName("gender") val gender: GenderDto,
    @SerializedName("name") val name: UserNameDto,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val picture: UserPictureDto
)

enum class GenderDto {
    @SerializedName("male")
    MALE,
    @SerializedName("female")
    FEMALE
}

data class UserNameDto(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)

data class UserPictureDto(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
)