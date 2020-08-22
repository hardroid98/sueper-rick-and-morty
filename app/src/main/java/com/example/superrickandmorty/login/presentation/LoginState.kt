package com.example.superrickandmorty.login.presentation

import com.example.superrickandmorty.login.domain.Usuario

sealed class LoginState(
    open val result: Usuario? = null,
    open val error: Throwable? = null
) {
    object LoadingState : LoginState()
    object ContrasenaIncorrecta : LoginState()
    data class Complete(override val result: Usuario?) : LoginState(result = result)
    data class Error(override val error: Throwable?) : LoginState(error = error)
}