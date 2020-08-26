package com.example.superrickandmorty.personajes.data.remote

import com.google.gson.annotations.SerializedName

data class PersonajesModel(
    @SerializedName("results")
    val personajes: List<PersonajeModel>? = null
)
