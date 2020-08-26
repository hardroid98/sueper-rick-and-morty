package com.example.superrickandmorty.registro.domain


interface RegistroRepository {

    suspend fun registraUsuario(usuario: RegistroUsuario) : Boolean
}