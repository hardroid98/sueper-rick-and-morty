package com.example.superrickandmorty.login.presentation

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superrickandmorty.login.domain.LoginUseCase
import com.example.superrickandmorty.login.domain.Usuario
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val liveData = MutableLiveData<LoginState>()

    fun getLiveData() = liveData

    fun doLogin(usuario: String, contrasena: String) {
        liveData.postValue(LoginState.LoadingState)
        viewModelScope.launch {
            try {
                val result = loginUseCase.execute(usuario, contrasena)
                handleResult(result)
            } catch (invalidCredentials: FirebaseAuthInvalidCredentialsException) {
                liveData.postValue(LoginState.ContrasenaIncorrecta)
            } catch (exception: Exception){
                liveData.postValue(LoginState.Error(exception))
            }
        }
    }

    private fun handleError(error: Throwable?) {
        liveData.postValue(LoginState.Error(error))
    }

    private fun handleResult(result: Usuario) {
        if (result.id.isNotEmpty()) {
            liveData.postValue(LoginState.Complete(result))
        } else {
            liveData.postValue(LoginState.ContrasenaIncorrecta)
        }
    }

}