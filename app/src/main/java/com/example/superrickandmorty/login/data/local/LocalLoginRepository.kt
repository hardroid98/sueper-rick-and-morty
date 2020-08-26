package com.example.superrickandmorty.login.data.local

import com.example.superrickandmorty.login.domain.LoginRepository
import com.example.superrickandmorty.login.domain.Usuario
import io.reactivex.Single

class LocalLoginRepository : LoginRepository {
    override suspend fun login(usuario: String, contrasena: String): Usuario {
        return if (usuario == "Admin" && contrasena == "1234") {
                Usuario("1", "Litto", "fotourl")
        } else {
                Usuario("", "", "")
        }
    }
}