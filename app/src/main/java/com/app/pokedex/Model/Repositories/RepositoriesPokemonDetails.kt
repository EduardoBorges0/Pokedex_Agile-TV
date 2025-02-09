package com.app.pokedex.Model.Repositories

import com.app.pokedex.Model.Entities.DetailsPokemon
import com.app.pokedex.Model.Entities.PokemonsEntities
import com.app.pokedex.Model.RetroFit.GETPokemonDetails

class RepositoriesPokemonDetails(private val getPokemonDetails: GETPokemonDetails) {
   suspend fun getPokemonsById(id: Int): DetailsPokemon {
       return getPokemonDetails.getPokemonById(id)
   }
 }