package com.example.superrickandmorty.registro.domain

class RegistraUsuarioUseCase(private val registraUsuarioRepository: RegistroRepository) {

    suspend fun excecute(registraUsuario: RegistroUsuario) = registraUsuarioRepository.registraUsuario(registraUsuario)
}