package com.app.pokedex.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokedex.Model.Entities.DetailsPokemon
import com.app.pokedex.Model.Entities.PokemonsEntities
import com.app.pokedex.Model.Repositories.RepositoriesPokemonDetails
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(private val repositoriesPokemonDetails: RepositoriesPokemonDetails) : ViewModel() {

    val _pokemonDetails = MutableLiveData<DetailsPokemon>()
    val pokemonsDetails : LiveData<DetailsPokemon> = _pokemonDetails

    fun getPokemonsById(id: Int) {
        viewModelScope.launch {
            try {
                _pokemonDetails.value = repositoriesPokemonDetails.getPokemonsById(id)

            } catch (e: Exception) {
                Log.e("PokemonDetails", "Error fetching data", e)
            }
        }
    }

}