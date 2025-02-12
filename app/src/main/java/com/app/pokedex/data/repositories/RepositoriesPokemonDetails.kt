package com.app.pokedex.data.repositories

import com.app.pokedex.data.model.entities.DetailsPokemon
import com.app.pokedex.data.network.GETPokemonDetails

class RepositoriesPokemonDetails(private val getPokemonDetails: GETPokemonDetails) {
   suspend fun getPokemonsById(id: Int): DetailsPokemon {
       return getPokemonDetails.getPokemonById(id)
   }
 }