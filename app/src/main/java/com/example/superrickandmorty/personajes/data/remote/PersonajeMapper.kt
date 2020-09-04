package com.example.superrickandmorty.personajes.data.remote

import com.example.superrickandmorty.personajes.domain.Personaje

class PersonajeMapper {
    //Mappers
    fun mapToEntity(personajeModel: PersonajeModel): Personaje {
        personajeModel.apply {
            return Personaje(id, name, image)
        }
    }
}