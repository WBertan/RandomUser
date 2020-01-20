package com.bertan.randomuser.app.di

import com.bertan.randomuser.util.SchedulerProvider
import com.bertan.randomuser.util.SchedulerProviderImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProviderImpl()
    }

}