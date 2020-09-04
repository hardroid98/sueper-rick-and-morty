package com.example.superrickandmorty.personajes.domain

class ObtenerPersonajesUseCase(
    private val personajesRepository: PersonajesRepository
) {
    suspend fun execute() = personajesRepository.obtenerPersonajes()
}