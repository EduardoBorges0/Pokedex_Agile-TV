package com.app.pokedex.data.network

import com.app.pokedex.data.model.entities.DetailsPokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface GETPokemonDetails {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): DetailsPokemon
}