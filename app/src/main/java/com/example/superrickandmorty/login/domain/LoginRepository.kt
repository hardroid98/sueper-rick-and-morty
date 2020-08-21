package com.example.superrickandmorty.login.domain

import io.reactivex.Single

interface LoginRepository {
    fun login(usuario: String, contrasena: String) : Single<Usuario>
}
