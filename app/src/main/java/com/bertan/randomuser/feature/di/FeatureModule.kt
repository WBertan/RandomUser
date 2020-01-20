package com.bertan.randomuser.feature.di

import com.bertan.randomuser.api.domain.GetRandomUsersUseCase
import com.bertan.randomuser.feature.presentation.viewmodel.UserListViewModelFactory
import com.bertan.randomuser.feature.presentation.viewmodel.mapper.UserViewDataMapper
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        FeatureFragmentBuilder::class
    ]
)
class FeatureModule {

    @Provides
    fun provideUserViewDataMapper(): UserViewDataMapper {
        return UserViewDataMapper()
    }

    @Provides
    fun provideUserListViewModelFactory(
        getRandomUsersUseCase: GetRandomUsersUseCase,
        userViewDataMapper: UserViewDataMapper
    ): UserListViewModelFactory {
        return UserListViewModelFactory(
            getRandomUsersUseCase,
            userViewDataMapper
        )
    }

}