package com.example.superrickandmorty.personajes.data.remote

import com.example.superrickandmorty.personajes.domain.Personajes
import com.example.superrickandmorty.personajes.domain.PersonajesRepository

class RemotePersonajeRepository(
    private val personajesApi: PersonajesApi,
    private val personajeMapper: PersonajeMapper
) : PersonajesRepository{
    
    override suspend fun obtenerPersonajes(): Personajes {
        val personajes = personajesApi.getPersonajes()

        val listaTransformada =
            personajes.personajes?.map { personajeMapper.mapToEntity(it) } ?: emptyList()

        return Personajes(listaTransformada)
    }

}