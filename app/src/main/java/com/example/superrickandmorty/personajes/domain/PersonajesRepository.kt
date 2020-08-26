package com.example.superrickandmorty.personajes.domain

import com.example.superrickandmorty.personajes.data.remote.PersonajesModel

interface PersonajesRepository {
    fun obtenerPersonajes() : PersonajesModel
}