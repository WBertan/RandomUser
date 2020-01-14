package com.bertan.randomuser.feature.di

import com.bertan.randomuser.feature.presentation.view.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeatureFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindUserListFragment(): UserListFragment

}