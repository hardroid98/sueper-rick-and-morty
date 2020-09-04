package com.example.superrickandmorty.personajes.presentation

import com.example.superrickandmorty.personajes.domain.Personajes

sealed class PersonajesState(
    open val result: Personajes? = null
){
    object LoadingPersonajesState: PersonajesState()
    data class LoadPersonajesState(override val result: Personajes): PersonajesState(result = result)
    object EmptyListPersonajesState: PersonajesState()
    object ErrorServerPersonajesState: PersonajesState()
    object NotInternetPersonajesState: PersonajesState()
}
