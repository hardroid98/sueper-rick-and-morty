package com.example.superrickandmorty.login.domain

import io.reactivex.Single

interface LoginRepository {
    suspend fun login(usuario: String, contrasena: String) : Usuario
}
