package com.bertan.randomuser.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bertan.randomuser.R
import com.bertan.randomuser.api.domain.GetRandomUsersUseCase
import com.bertan.randomuser.api.domain.data.UserData
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var useCase: GetRandomUsersUseCase

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        useCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .let(compositeDisposable::add)
    }

    private fun onSuccess(result: List<UserData>) {
        text.text = result.size.toString()
    }

    private fun onError(error: Throwable) {
        text.text = error.message
    }

    override fun onPause() {
        compositeDisposable.dispose()
        super.onPause()
    }

}
