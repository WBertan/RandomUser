package com.bertan.randomuser.app

import android.app.Application
import com.bertan.randomuser.app.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RandomUserApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.create()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}