package com.bertan.randomuser.api.di

import com.bertan.randomuser.api.domain.GetRandomUsersUseCase
import com.bertan.randomuser.api.domain.GetRandomUsersUseCaseImpl
import com.bertan.randomuser.api.domain.mapper.UserDataMapper
import com.bertan.randomuser.api.repository.UserRepository
import com.bertan.randomuser.api.repository.UserRepositoryImpl
import com.bertan.randomuser.api.repository.network.UserApi
import com.bertan.randomuser.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    fun provideUserRepository(
        userApi: UserApi,
        schedulerProvider: SchedulerProvider
    ): UserRepository {
        return UserRepositoryImpl(userApi, schedulerProvider)
    }

    @Provides
    fun provideUserDataMapper(): UserDataMapper {
        return UserDataMapper()
    }

    @Provides
    fun provideGetRandomUsersUseCase(
        userRepository: UserRepository,
        userDataMapper: UserDataMapper
    ): GetRandomUsersUseCase {
        return GetRandomUsersUseCaseImpl(userRepository, userDataMapper)
    }

}