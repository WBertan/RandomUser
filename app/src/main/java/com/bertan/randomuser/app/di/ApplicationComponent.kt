package com.bertan.randomuser.app.di

import com.bertan.randomuser.api.di.ApiModule
import com.bertan.randomuser.app.RandomUserApplication
import com.bertan.randomuser.feature.di.FeatureModule
import com.bertan.randomuser.network.di.NetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        FeatureModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: RandomUserApplication)

}

