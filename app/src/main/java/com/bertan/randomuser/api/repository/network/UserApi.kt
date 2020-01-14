package com.bertan.randomuser.api.repository.network

import com.bertan.randomuser.api.repository.model.UserResultsDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/?nat=gb&noinfo")
    fun getRandomUsers(@Query("results") maxResults: Int): Single<UserResultsDto>

}