package com.bertan.randomuser.app.di

import com.bertan.randomuser.api.di.NetworkModule
import com.bertan.randomuser.api.di.UserApiModule
import com.bertan.randomuser.app.RandomUserApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        UserApiModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: RandomUserApplication)

}

