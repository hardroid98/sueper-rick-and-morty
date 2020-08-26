package com.example.superrickandmorty.personajes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.superrickandmorty.personajes.domain.ObtenerPersonajesUseCase

class PersonajesViewModelFactory(
    private val obtenerPersonajesUseCase: ObtenerPersonajesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ObtenerPersonajesUseCase::class.java)
            .newInstance(obtenerPersonajesUseCase)
    }


}