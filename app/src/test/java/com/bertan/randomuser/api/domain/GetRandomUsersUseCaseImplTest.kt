package com.bertan.randomuser.api.domain

import com.bertan.randomuser.api.domain.data.UserData
import com.bertan.randomuser.api.domain.mapper.UserDataMapper
import com.bertan.randomuser.api.repository.UserRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetRandomUsersUseCaseImplTest {

    private val userRepository: UserRepository = mock()
    private val userDataMapper: UserDataMapper = mock()

    private lateinit var useCase: GetRandomUsersUseCaseImpl

    @Before
    fun setup() {
        useCase = GetRandomUsersUseCaseImpl(userRepository, userDataMapper)
    }

    private fun mockRepositorySuccess() {
        whenever(userRepository.getRandomUsers()).thenReturn(Single.just(listOf(mock())))
    }

    private fun mockRepositoryError(): Error {
        return Error("dummyError").also {
            whenever(userRepository.getRandomUsers()).thenReturn(Single.error(it))
        }
    }

    private fun mockMapperSuccess(result: UserData) {
        whenever(userDataMapper.toData(any())).thenReturn(result)
    }

    private fun mockMapperError(): Error {
        return Error("dummyError").also {
            whenever(userDataMapper.toData(any())).thenThrow(it)
        }
    }

    @Test
    fun givenRepositorySuccess_and_mapperSuccess_whenExecute_thenReturnResults() {
        mockRepositorySuccess()
        val expectedResult: UserData = mock()
        mockMapperSuccess(expectedResult)

        useCase.execute()
            .test()
            .assertComplete()
            .assertResult(listOf(expectedResult))
    }

    @Test
    fun givenRepositoryError_whenExecute_thenReturnError() {
        val error = mockRepositoryError()

        useCase.execute()
            .test()
            .assertError(error)

        verifyZeroInteractions(userDataMapper)
    }

    @Test
    fun givenRepositorySuccess_and_mapperError_whenExecute_thenReturnError() {
        mockRepositorySuccess()
        val error = mockMapperError()

        useCase.execute()
            .test()
            .assertError(error)
    }

}