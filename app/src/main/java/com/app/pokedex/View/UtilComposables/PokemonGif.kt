package com.app.pokedex.View.UtilComposables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import com.app.pokedex.ViewModel.PokemonsViewModel

@Composable
fun PokemonGif(
    url: String) {
    val context = LocalContext.current
    val imageLoader = ImageLoader
        .Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()
    val heightDp = LocalConfiguration.current.screenHeightDp

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(heightDp.dp / 2)
        .padding(horizontal = 25.dp)
        .padding(top = 120.dp)
        .clip(RoundedCornerShape(8.dp, 8.dp, 8.dp, 42.dp))
        .border(BorderStroke(32.dp, Color(0xFFDEDFDF)))
        .background(Color.White)) {

        AsyncImage(
            model = url,
            contentDescription = "Pokemons",
            imageLoader = imageLoader,
            modifier = Modifier
                .align(Alignment.Center)
                .size(heightDp.dp / 5)
        )
    }
}