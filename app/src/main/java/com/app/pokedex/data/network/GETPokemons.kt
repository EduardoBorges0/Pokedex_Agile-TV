package com.app.pokedex.data.network

import com.app.pokedex.data.model.entities.PokemonsEntities
import retrofit2.http.GET

interface GETPokemons {
    @GET("pokemon/?offset=0&limit=150")
    suspend fun getAllPokemons(): PokemonsEntities
}
