package com.app.pokedex.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokedex.data.model.entities.DetailsPokemon
import com.app.pokedex.data.repositories.RepositoriesPokemonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(private val repositoriesPokemonDetails: RepositoriesPokemonDetails) : ViewModel() {

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