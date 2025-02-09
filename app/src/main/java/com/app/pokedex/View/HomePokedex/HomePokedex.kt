package com.app.pokedex.View.HomePokedex

import android.text.format.DateUtils
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
import com.app.pokedex.View.UtilComposables.ClickableControlCross
import com.app.pokedex.View.UtilComposables.PokemonGif
import com.app.pokedex.ViewModel.PokemonDetailsViewModel
import com.app.pokedex.ViewModel.PokemonsViewModel
import com.app.pokedex.ui.theme.backgroundColor

@Composable
fun HomePokedex(pokemonsViewModel: PokemonsViewModel, navController: NavController, pokemonDetailsViewModel: PokemonDetailsViewModel) {
    val pokemons by pokemonsViewModel.pokemonsLive.observeAsState()
    val getAllPokemonsId = pokemonsViewModel.getAllPokemonsId

    val spritePokemon = pokemons?.results?.get(getAllPokemonsId)?.spriteUrl
    val pokemonName = pokemons?.results?.get(getAllPokemonsId)?.name.toString()
    val pokemonId = pokemons?.results?.get(getAllPokemonsId)?.id.toString()

    val context = LocalContext.current
    val heightDp = LocalConfiguration.current.screenHeightDp

    Box(modifier = Modifier
        .background(backgroundColor)

        .fillMaxSize()

    ){
        AsyncImage(
            model = R.drawable.background,
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp))
        {
            CircleTopPokedex()

            PokemonGif(spritePokemon.toString())

            PokemonNameDisplay(
                modifier = Modifier
                .align(Alignment.CenterStart),
                pokemonName,
                pokemonId
            )

            ClickableControlCross(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(y = heightDp.dp / 5, x = -15.dp
                    ),

                onUpClick = {
                    navController.navigate("details")
                    pokemonsViewModel.getSearchPokemonsId = pokemonId.toInt().minus(1)
                    Utils.playSound(context)
                },

                onDownClick = {
                    navController.navigate("search")
                    pokemonsViewModel.resetCount()
                    Utils.playSound(context)
                },

                onLeftClick = {
                    if(getAllPokemonsId > 0){
                        pokemonsViewModel.decrementCount()
                    }
                    Utils.playSound(context)
                },

                onRightClick = {
                    if(getAllPokemonsId < 149){
                        pokemonsViewModel.incrementCount()
                    }
                    Utils.playSound(context)
                }
            )
        }
        }
}
