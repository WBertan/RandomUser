package com.bertan.randomuser.api.repository

import com.bertan.randomuser.api.repository.model.*
import com.bertan.randomuser.api.repository.network.UserApi
import com.bertan.randomuser.util.SchedulerProvider
import com.bertan.randomuser.util.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {

    private val userApi: UserApi = mock()
    private val schedulerProvider: SchedulerProvider = TestSchedulerProvider()

    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        repository = UserRepositoryImpl(userApi, schedulerProvider)
    }

    private fun mockSuccessApi(result: List<UserDto>) {
        whenever(userApi.getRandomUsers(any())).thenReturn(Single.just(UserResultsDto(result)))
    }

    private fun mockErrorApi(): Error {
        return Error("dummyError").also {
            whenever(userApi.getRandomUsers(any())).thenReturn(Single.error(it))
        }
    }

    @Test
    fun givenSuccess_whenGetRandomUsers_thenReturnResults() {
        val mockedResults = listOf(
            UserDto(
                gender = GenderDto.MALE,
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
        )
        mockSuccessApi(mockedResults)

        repository.getRandomUsers()
            .test()
            .assertComplete()
            .assertResult(mockedResults)
    }

    @Test
    fun givenSuccessWithEmptyUsers_whenGetRandomUsers_thenReturnEmptyResults() {
        val mockedResults = emptyList<UserDto>()
        mockSuccessApi(mockedResults)

        repository.getRandomUsers()
            .test()
            .assertComplete()
            .assertResult(mockedResults)
    }

    @Test
    fun givenError_whenGetRandomUsers_thenReturnApiError() {
        val error = mockErrorApi()

        repository.getRandomUsers()
            .test()
            .assertError(error)
    }

}