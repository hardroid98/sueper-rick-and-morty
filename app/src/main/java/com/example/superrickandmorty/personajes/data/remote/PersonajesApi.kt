package com.example.superrickandmorty.personajes.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface PersonajesApi {
   @GET("character")
   suspend fun getPersonajes() : Response<PersonajesModel>

}