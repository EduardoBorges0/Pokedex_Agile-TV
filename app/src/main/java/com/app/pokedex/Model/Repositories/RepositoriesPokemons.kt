package com.app.pokedex.Model.Repositories

import com.app.pokedex.Model.Entities.PokemonsEntities
import com.app.pokedex.Model.RetroFit.GETPokemons

class RepositoriesPokemons(private val getPokemons: GETPokemons) {
    suspend fun getAllPokemons() : PokemonsEntities{
        return getPokemons.getAllPokemons()
    }
}