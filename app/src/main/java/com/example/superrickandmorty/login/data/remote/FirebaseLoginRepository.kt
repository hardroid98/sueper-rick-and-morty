package com.example.superrickandmorty.login.data.remote

import com.example.superrickandmorty.login.domain.LoginRepository
import com.example.superrickandmorty.login.domain.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single
import kotlinx.coroutines.tasks.await

class FirebaseLoginRepository(
    private val firebaseAuth: FirebaseAuth
) : LoginRepository {
    private suspend fun authenticate(
        email: String,
        password: String
    ): FirebaseUser? {
        firebaseAuth.signInWithEmailAndPassword(
            email, password
        ).await()
        return firebaseAuth.currentUser ?: throw FirebaseAuthException("", "")
    }

    override suspend fun login(usuario: String, contrasena: String): Usuario {
        val result = authenticate(usuario, contrasena)
        return Usuario("1", result?.displayName ?: "", result?.email ?: "")
    }
}