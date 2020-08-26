package com.example.superrickandmorty.registro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.superrickandmorty.registro.domain.RegistraUsuarioUseCase

class RegistroViewModelFactory(
    private val registraUsuarioUseCase: RegistraUsuarioUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegistraUsuarioUseCase::class.java)
            .newInstance(registraUsuarioUseCase)
    }
}