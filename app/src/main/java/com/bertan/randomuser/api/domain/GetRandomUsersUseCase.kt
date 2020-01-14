package com.bertan.randomuser.api.domain

import com.bertan.randomuser.api.domain.data.UserData
import com.bertan.randomuser.api.domain.mapper.UserDataMapper
import com.bertan.randomuser.api.repository.UserRepository
import io.reactivex.Single

interface GetRandomUsersUseCase {

    fun execute(): Single<List<UserData>>

}

class GetRandomUsersUseCaseImpl(
    private val userRepository: UserRepository,
    private val userDataMapper: UserDataMapper
) : GetRandomUsersUseCase {

    override fun execute(): Single<List<UserData>> = userRepository.getRandomUsers()
        .map { it.map(userDataMapper::toData) }

}