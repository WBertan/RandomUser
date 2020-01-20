package com.bertan.randomuser.api.repository

import com.bertan.randomuser.api.repository.model.UserDto
import com.bertan.randomuser.api.repository.network.UserApi
import com.bertan.randomuser.util.SchedulerProvider
import io.reactivex.Single

interface UserRepository {

    fun getRandomUsers(): Single<List<UserDto>>

}

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val schedulerProvider: SchedulerProvider
) : UserRepository {

    companion object {
        private const val MAX_USERS = 30
    }

    override fun getRandomUsers(): Single<List<UserDto>> =
        userApi.getRandomUsers(MAX_USERS).map { it.users }
            .subscribeOn(schedulerProvider.io())

}