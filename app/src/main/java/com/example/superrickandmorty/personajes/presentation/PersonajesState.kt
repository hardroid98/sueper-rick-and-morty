package com.example.superrickandmorty.personajes.presentation

import com.example.superrickandmorty.personajes.data.remote.PersonajeModel
import com.example.superrickandmorty.personajes.data.remote.PersonajesModel

sealed class PersonajesState(
    open val result: PersonajesModel? = null
){
    object LoadingPersonajesState: PersonajesState()
    data class LoadPersonajesState(override val result: PersonajesModel): PersonajesState(result = result)
    object EmptyListPersonajesState: PersonajesState()
    object ErrorServerPersonajesState: PersonajesState()
    object NotInternetPersonajesState: PersonajesState()
}
