package com.app.pokedex.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pokedex.Model.Entities.PokemonsEntities
import com.app.pokedex.Model.Entities.Results
import com.app.pokedex.Model.Repositories.RepositoriesPokemons
import kotlinx.coroutines.launch
import java.io.IOException

class PokemonsViewModel(private val repositoriesPokemons: RepositoriesPokemons) : ViewModel() {
    private val _pokemonsLive = MutableLiveData<PokemonsEntities>()
    val pokemonsLive: LiveData<PokemonsEntities> = _pokemonsLive

    private val _searchResults = MutableLiveData<List<Results>>()
    val searchResults: LiveData<List<Results>> = _searchResults

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var getAllPokemonsId by mutableStateOf(0)
    var getSearchPokemonsId by mutableStateOf(0)


    init {
        getAllPokemons()
    }

    fun getAllPokemons() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _pokemonsLive.value = repositoriesPokemons.getAllPokemons()
                _isLoading.value = false

            } catch (e: IOException) {
                _errorLiveData.value = "Network error. Please check your connection."
                Log.e("PokemonsViewModel", "Network error", e)
            } catch (e: Exception) {
                _errorLiveData.value = "An unexpected error occurred. Please try again."
                Log.e("PokemonsViewModel", "Error fetching pokemons", e)
            }
        }
    }
     fun incrementCount(){
         getAllPokemonsId += 1
    }
     fun decrementCount(){
         getAllPokemonsId -= 1
    }
     fun resetCount(){
         getAllPokemonsId = 0
    }

    fun searchPokemons(searchValue: String) {
        val allPokemons = _pokemonsLive.value
        _isLoading.value = true
        if (!searchValue.isBlank() && allPokemons != null) {
            val filteredResults = allPokemons.results.filter {
                it.name.contains(searchValue, ignoreCase = true)
            }
            _isLoading.value = false

            _searchResults.value = filteredResults
        } else {
            _searchResults.value = emptyList()
        }
    }
}