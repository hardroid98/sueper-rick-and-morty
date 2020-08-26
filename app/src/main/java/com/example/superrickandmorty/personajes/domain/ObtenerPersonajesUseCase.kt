package com.example.superrickandmorty.personajes.domain

class ObtenerPersonajesUseCase(
    private val personajesRepository: PersonajesRepository
) {
    fun execute() = personajesRepository.obtenerPersonajes()
}