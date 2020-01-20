package com.bertan.randomuser.util

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun mainThread(): Scheduler = Schedulers.trampoline()

}