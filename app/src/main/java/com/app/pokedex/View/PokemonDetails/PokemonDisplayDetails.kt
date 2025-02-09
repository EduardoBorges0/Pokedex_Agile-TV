package com.app.pokedex.View.PokemonDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pokedex.View.UtilComposables.TypewriterText
import com.app.pokedex.ViewModel.PokemonDetailsViewModel
import com.app.pokedex.ui.theme.buttonsColor

@Composable
fun PokemonDisplayDetails(modifier: Modifier,
                       pokemonName: String,
                          getSearchPokemonsId: Int,
                          pokemonId: String,
                          pokemonDetailsViewModel: PokemonDetailsViewModel) {
    val pokemonDetails by pokemonDetailsViewModel.pokemonsDetails.observeAsState()

    pokemonDetailsViewModel.getPokemonsById(getSearchPokemonsId + 1)

    val types = pokemonDetails?.types?.map { it?.type?.name.toString() } ?: emptyList()
    val abilities = pokemonDetails?.abilities?.map { it?.ability?.name.toString() } ?: emptyList()

    val widthDp = LocalConfiguration.current.screenWidthDp
    val heightDp = LocalConfiguration.current.screenHeightDp

    Box(
        modifier = modifier
            .offset(x = 30.dp, y = heightDp.dp / 4)
            .width(widthDp.dp - 190.dp)
            .heightIn(max = 350.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(buttonsColor)
            .border(BorderStroke(7.dp, Color(0xFFDEDFDF)), RoundedCornerShape(8.dp))

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xFF789678).copy(alpha = 0.5f),
                            Color.Transparent
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(300f, 500f)
                    )
                )
        )
        TypewriterText(
            text = pokemonName,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 40.dp),
            color = Color.White,
            fontSize = 17.sp,
        )
        TypewriterText(
            text = "#$pokemonId",
            modifier = Modifier.align(Alignment.Center).padding(bottom = 150.dp),
            color = Color.White,
            fontSize = 15.sp,
        )
        TypewriterText(
            text = "Tipos:",
            modifier = Modifier.align(Alignment.Center).padding(bottom = 80.dp),
            color = Color.White,
            fontSize = 15.sp,
        )
        types.forEachIndexed { index, typeName ->
            TypewriterText(
                text = typeName,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = if (index > 0) 50.dp else 0.dp),
                color = Color.White,
                fontSize = 13.sp,
            )
        }
        TypewriterText(
            text = "Habilidade:",
            modifier = Modifier.align(Alignment.Center).padding(top = 130.dp),
            color = Color.White,
            fontSize = 15.sp,
        )
        abilities.forEachIndexed { index, abilitiesName ->
            TypewriterText(
                text = abilitiesName,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 200.dp + (index * 80).dp),
                color = Color.White,
                fontSize = 13.sp,
            )
        }
    }
}
