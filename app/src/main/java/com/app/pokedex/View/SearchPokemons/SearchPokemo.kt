package com.app.pokedex.View.SearchPokemons

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.app.pokedex.R
import com.app.pokedex.Utils.Utils
import com.app.pokedex.View.HomePokedex.CircleTopPokedex
import com.app.pokedex.View.UtilComposables.ClickableControlCross
import com.app.pokedex.View.UtilComposables.PokemonGif
import com.app.pokedex.View.HomePokedex.PokemonNameDisplay
import com.app.pokedex.ViewModel.PokemonsViewModel
import com.app.pokedex.ui.theme.backgroundColor

@Composable
fun SearchPokemon(pokemonsViewModel: PokemonsViewModel, navController: NavController) {
    val searchPokemons by pokemonsViewModel.searchResults.observeAsState(emptyList())
    val context = LocalContext.current
    val heightDp = LocalConfiguration.current.screenHeightDp

    val pokemon = searchPokemons.getOrNull(pokemonsViewModel.getAllPokemonsId)
    val pokemonName = pokemon?.name.orEmpty()
    val pokemonId = pokemon?.id.toString()
    val pokemonSpriteUrl = pokemon?.spriteUrl.orEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)

            .padding(top = 40.dp)
    ) {
        AsyncImage(
            model = R.drawable.background,
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        TextFieldSearchPokemon(pokemonsViewModel)

        CircleTopPokedex()

        PokemonGif(pokemonSpriteUrl)

        PokemonNameDisplay(
            modifier = Modifier.align(Alignment.CenterStart),
            pokemonName = pokemonName,
            pokemonId = pokemonId
        )

        ClickableControlCross(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(y = heightDp.dp / 5, x = -15.dp),
            onUpClick = {
                pokemonsViewModel.resetCount()
                navController.navigate("home")
                Utils.playSound(context)
            },
            onDownClick = {
                if (pokemonsViewModel.getSearchPokemonsId > 0) {
                    pokemonsViewModel.getAllPokemonsId = pokemonsViewModel.getSearchPokemonsId - 1
                }
                navController.navigate("details")
            },
            onLeftClick = {
                if (pokemonsViewModel.getAllPokemonsId > 0) {
                    pokemonsViewModel.decrementCount()
                    pokemonsViewModel.getSearchPokemonsId = searchPokemons.getOrNull(pokemonsViewModel.getAllPokemonsId)?.id?.toInt() ?: 0
                    Utils.playSound(context)
                }
            },
            onRightClick = {
                if (pokemonsViewModel.getAllPokemonsId < (searchPokemons.size - 1)) {
                    pokemonsViewModel.incrementCount()
                    pokemonsViewModel.getSearchPokemonsId = searchPokemons.getOrNull(pokemonsViewModel.getAllPokemonsId)?.id?.toInt() ?: 0
                    Utils.playSound(context)
                }
            }
        )
    }
}
