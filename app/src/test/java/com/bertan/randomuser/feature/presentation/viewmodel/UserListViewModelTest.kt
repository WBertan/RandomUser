package com.bertan.randomuser.feature.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bertan.randomuser.api.domain.GetRandomUsersUseCase
import com.bertan.randomuser.feature.presentation.view.data.Error
import com.bertan.randomuser.feature.presentation.view.data.UserViewData
import com.bertan.randomuser.feature.presentation.viewmodel.mapper.UserViewDataMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserListViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val getRandomUsersUseCase: GetRandomUsersUseCase = mock()
    private val userViewDataMapper: UserViewDataMapper = mock()

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setup() {
        viewModel = UserListViewModel(
            getRandomUsersUseCase,
            userViewDataMapper
        )
    }

    private val defaultUserViewData: UserViewData = UserViewData(
        displayName = "displayNameValue",
        email = "emailValue",
        thumbnail = "thumbnailValue",
        backgroundColor = "backgroundColorValue"
    )

    private fun mockGetUsersSuccess() {
        whenever(getRandomUsersUseCase.execute()).thenReturn(Single.just(listOf(mock())))
    }

    private fun mockGetUsersError(): Throwable {
        return Throwable("dummyError").also {
            whenever(getRandomUsersUseCase.execute()).thenReturn(Single.error(it))
        }
    }

    private fun mockMapperSuccess() {
        whenever(userViewDataMapper.toViewData(any())).thenReturn(defaultUserViewData)
    }

    private fun mockMapperError(): Throwable {
        return Throwable("dummyError").also { exception ->
            whenever(userViewDataMapper.toViewData(any())).thenAnswer { throw exception }
        }
    }

    @Test
    fun givenGetUsersSuccess_and_MapperSuccess_whenStart_thenPostUsersViewData() {
        mockGetUsersSuccess()
        mockMapperSuccess()

        val expectedResult = listOf(defaultUserViewData)

        viewModel.start()

        assertNull(viewModel.events.value)
        val result = viewModel.viewData.value
        assertEquals(expectedResult, result)
    }

    @Test
    fun givenGetUsersError_whenStart_thenPostErrorEvent() {
        val error = mockGetUsersError()

        val expectedResult = Error(error)

        viewModel.start()

        assertNull(viewModel.viewData.value)
        val result = viewModel.events.value
        assertEquals(expectedResult, result)
    }

    @Test
    fun givenMapperError_whenStart_thenPostErrorEvent() {
        mockGetUsersSuccess()
        val error = mockMapperError()

        val expectedResult = Error(error)

        viewModel.start()

        assertNull(viewModel.viewData.value)
        val result = viewModel.events.value
        assertEquals(expectedResult, result)
    }

}