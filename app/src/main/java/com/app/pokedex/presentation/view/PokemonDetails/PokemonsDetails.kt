package com.app.pokedex.presentation.view.PokemonDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.app.pokedex.presentation.view.HomePokedex.CircleTopPokedex
import com.app.pokedex.presentation.view.UtilComposables.ClickableControlCross
import com.app.pokedex.presentation.view.UtilComposables.PokemonGif
import com.app.pokedex.presentation.viewmodel.PokemonDetailsViewModel
import com.app.pokedex.presentation.viewmodel.PokemonsViewModel
import com.app.pokedex.ui.theme.backgroundColor

@Composable
fun PokemonsDetails(
    pokemonDetailsViewModel: PokemonDetailsViewModel,
    pokemonsViewModel: PokemonsViewModel,
    navController: NavController
) {
    val pokemons by pokemonsViewModel.pokemonsLive.observeAsState()

    val spritesUrl = pokemons?.results?.get(pokemonsViewModel.getAllPokemonsId)?.spriteUrl.toString()
    val pokemonName = pokemons?.results?.get(pokemonsViewModel.getAllPokemonsId)?.name.toString()
    val pokemonId = pokemons?.results?.get(pokemonsViewModel.getAllPokemonsId)?.id.toString()
    val context = LocalContext.current
    val heightDp = LocalConfiguration.current.screenHeightDp



    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
        .padding(top = 40.dp)
    ){
        AsyncImage(
            model = R.drawable.background,
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        CircleTopPokedex()

         PokemonGif(spritesUrl)

        PokemonDisplayDetails(
            modifier = Modifier
            .align(Alignment.CenterStart)
            .wrapContentHeight(),
            pokemonName = pokemonName,
            getSearchPokemonsId = pokemonsViewModel.getAllPokemonsId,
            pokemonId = pokemonId,
            pokemonDetailsViewModel = pokemonDetailsViewModel
            )

        ClickableControlCross(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(y = heightDp.dp / 5, x = -15.dp
                ),

            onUpClick = {
                pokemonsViewModel.resetCount()
                pokemonsViewModel.getSearchPokemonsId = 0
                navController.navigate("search")
                Utils.playSound(context)
            },
            onDownClick = {

                navController.navigate("home")
                Utils.playSound(context)
            },
            onLeftClick = {            },
            onRightClick = {}
        )
    }

}
