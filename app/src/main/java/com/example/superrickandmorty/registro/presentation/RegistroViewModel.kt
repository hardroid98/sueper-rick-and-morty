package com.example.superrickandmorty.registro.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superrickandmorty.login.domain.LoginUseCase
import com.example.superrickandmorty.registro.domain.RegistraUsuarioUseCase
import com.example.superrickandmorty.registro.domain.RegistroUsuario
import com.example.superrickandmorty.registro.presentation.RegistroUiState.*
import kotlinx.coroutines.launch
import java.lang.Exception

class RegistroViewModel(
    private val registraUsuarioUseCase: RegistraUsuarioUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<RegistroUiState>()

    fun getLiveData() = liveData

    fun registraUsuario(){
        liveData.postValue(LoadingRegistroUiState)
        viewModelScope.launch {
            try {
                val result = registraUsuarioUseCase.excecute(RegistroUsuario("m4rsh4ll", "m4rsh4ll2@malditosnakamas.cl", "12345678"))
                handleResult(result)
            }catch (exception: Exception){
                liveData.postValue(ErrorRegistroUiState(exception))
            }
        }
    }

    private fun handleResult(result: Boolean) {
        if(result){
            liveData.postValue(SuccessRegistroUiState(result))
        } else {
            liveData.postValue(UsuarioNoDisponibleRegistroUiState)
        }
    }
}