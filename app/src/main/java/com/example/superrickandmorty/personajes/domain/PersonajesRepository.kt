package com.example.superrickandmorty.personajes.domain

interface PersonajesRepository {
    suspend fun obtenerPersonajes() : Personajes
}