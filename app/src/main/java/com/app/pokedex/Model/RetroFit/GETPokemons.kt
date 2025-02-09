package com.app.pokedex.Model.RetroFit

import com.app.pokedex.Model.Entities.PokemonsEntities
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GETPokemons {
    @GET("pokemon/?offset=0&limit=150")
    suspend fun getAllPokemons() : PokemonsEntities

    companion object{
        private val pokedexRetroFitRepositories : GETPokemons by lazy {
            val api = Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            api.create(GETPokemons::class.java)
            }
        fun getInstance() : GETPokemons{
            return pokedexRetroFitRepositories
        }


    }
}