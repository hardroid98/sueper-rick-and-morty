package com.example.superrickandmorty.personajes.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superrickandmorty.personajes.data.remote.PersonajesModel
import com.example.superrickandmorty.personajes.domain.ObtenerPersonajesUseCase
import com.example.superrickandmorty.personajes.domain.Personajes
import com.example.superrickandmorty.personajes.presentation.PersonajesState.EmptyListPersonajesState
import com.example.superrickandmorty.personajes.presentation.PersonajesState.LoadingPersonajesState
import kotlinx.coroutines.launch

class PersonajesViewModel(
    private val obtenerPersonajesUseCase: ObtenerPersonajesUseCase
) : ViewModel(){

    private val liveData = MutableLiveData<PersonajesState>()

    fun getLiveData() = liveData

    fun obtenerPersonajes(){
        liveData.postValue(LoadingPersonajesState)
        viewModelScope.launch {
            val response = obtenerPersonajesUseCase.execute()
            handleResponse(response)
        }
    }

    private fun handleResponse(response: Personajes) {
        response.result.let {
            if(it.isNotEmpty()){
                liveData.postValue(PersonajesState.LoadPersonajesState(response))
            }else{
                liveData.postValue(EmptyListPersonajesState)
            }
        }

    }
}