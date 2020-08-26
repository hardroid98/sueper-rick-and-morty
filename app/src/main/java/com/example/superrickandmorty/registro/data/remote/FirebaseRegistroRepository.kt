package com.example.superrickandmorty.registro.data.remote

import com.example.superrickandmorty.registro.domain.RegistroRepository
import com.example.superrickandmorty.registro.domain.RegistroUsuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await

class FirebaseRegistroRepository (
    private val firebaseAuth: FirebaseAuth
) : RegistroRepository{

    override suspend fun registraUsuario(usuario: RegistroUsuario): Boolean {
        firebaseAuth.createUserWithEmailAndPassword(
            usuario.correo, usuario.clave
        ).await()
        return firebaseAuth.currentUser != null ?: throw FirebaseAuthException("", "")
    }

}