package com.example.superrickandmorty.registro.presentation

sealed class RegistroUiState(
    open val result: Boolean = false,
    open val error: Throwable? = null
){
  object LoadingRegistroUiState : RegistroUiState()
  data class SuccessRegistroUiState(override val result: Boolean) : RegistroUiState(result = result)
  data class ErrorRegistroUiState(override val error: Throwable) : RegistroUiState(error = error)
  object UsuarioNoDisponibleRegistroUiState : RegistroUiState()
}