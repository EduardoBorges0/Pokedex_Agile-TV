package com.app.pokedex.presentation.view.Nav

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.pokedex.Utils.MusicService
import com.app.pokedex.presentation.view.HomePokedex.HomePokedex
import com.app.pokedex.presentation.view.PokemonDetails.PokemonsDetails
import com.app.pokedex.presentation.view.SearchPokemons.SearchPokemon
import com.app.pokedex.presentation.viewmodel.PokemonDetailsViewModel
import com.app.pokedex.presentation.viewmodel.PokemonsViewModel
import com.app.pokedex.ui.theme.PokedexTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : ComponentActivity() {
    private val pokemonsViewModel: PokemonsViewModel by viewModels()
    private val pokemonDetailsViewModel: PokemonDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                NavHostComposable(pokemonDetailsViewModel, pokemonsViewModel)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostComposable(pokemonDetailsViewModel: PokemonDetailsViewModel, pokemonsViewModel: PokemonsViewModel) {
    val navHostController = rememberNavController()
    val context = LocalContext.current
    val intent = Intent(context, MusicService::class.java)

    context.startService(intent)

    AnimatedNavHost(
        navController = navHostController,
        startDestination = "home",
        enterTransition = {
            when {
                targetState.destination.route == "search" && initialState.destination.route == "home" ->
                    slideInVertically(initialOffsetY = { it }, animationSpec = tween(500)) // Home para Search
                targetState.destination.route == "details" && initialState.destination.route == "home" ->
                    slideInVertically(initialOffsetY = { -it }, animationSpec = tween(500)) // Home para Details
                targetState.destination.route == "home" && initialState.destination.route == "search" ->
                    slideInVertically(initialOffsetY = { -it }, animationSpec = tween(500)) // Search para Home
                targetState.destination.route == "details" && initialState.destination.route == "search" ->
                    slideInVertically(initialOffsetY = { it }, animationSpec = tween(500)) // Search para Details
                targetState.destination.route == "home" && initialState.destination.route == "details" ->
                    slideInVertically(initialOffsetY = { it }, animationSpec = tween(500))
                targetState.destination.route == "search" && initialState.destination.route == "details" ->
                    slideInVertically(initialOffsetY = { -it }, animationSpec = tween(500))// Details para Home
                else -> EnterTransition.None
            }
        },

    ) {
        composable("home") {
            HomePokedex(navController = navHostController, pokemonsViewModel = pokemonsViewModel, pokemonDetailsViewModel = pokemonDetailsViewModel)
        }
        composable("search") {
            SearchPokemon(pokemonsViewModel, navHostController)
        }
        composable("details") {
            PokemonsDetails(pokemonDetailsViewModel, pokemonsViewModel, navHostController)
        }
    }

}
