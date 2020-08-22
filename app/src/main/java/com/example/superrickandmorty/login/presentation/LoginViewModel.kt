package com.example.superrickandmorty.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superrickandmorty.login.domain.LoginUseCase
import com.example.superrickandmorty.login.domain.Usuario
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel (private val loginUseCase: LoginUseCase): ViewModel() {

    private val liveData = MutableLiveData<Usuario>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun doLogin(usuario : String, contrasena: String){
        compositeDisposable.add( loginUseCase
            .execute(usuario, contrasena)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleResult(result)},
                { error -> handleError (error)}
            )
        )
    }

    private fun handleError(error: Throwable?) {

    }

    private fun handleResult(result: Usuario?) {
        liveData.postValue(result)
    }


}