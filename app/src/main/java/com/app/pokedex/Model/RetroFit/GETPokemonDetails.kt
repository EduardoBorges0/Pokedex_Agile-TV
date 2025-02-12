package com.app.pokedex.Model.RetroFit

import com.app.pokedex.Model.Entities.DetailsPokemon
import com.app.pokedex.Model.Entities.PokemonsEntities
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GETPokemonDetails {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): DetailsPokemon
}