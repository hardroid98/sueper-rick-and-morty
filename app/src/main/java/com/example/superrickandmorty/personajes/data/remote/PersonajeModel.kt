package com.example.superrickandmorty.personajes.data.remote

data class PersonajeModel(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val created: String? = null,
    val episode: List<String>? = null
)