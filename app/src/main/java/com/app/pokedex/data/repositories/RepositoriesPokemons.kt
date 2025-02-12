package com.app.pokedex.data.repositories

import com.app.pokedex.data.model.entities.PokemonsEntities
import com.app.pokedex.data.network.GETPokemons

class RepositoriesPokemons(private val getPokemons: GETPokemons) {
    suspend fun getAllPokemons() : PokemonsEntities {
        return getPokemons.getAllPokemons()
    }
}