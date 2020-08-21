package com.example.superrickandmorty.login.domain

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    fun execute(usuario: String, contrasena: String) = loginRepository.login(usuario, contrasena)
}