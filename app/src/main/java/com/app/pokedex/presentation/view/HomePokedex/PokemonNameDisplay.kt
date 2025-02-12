package com.app.pokedex.presentation.view.HomePokedex

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pokedex.R
import com.app.pokedex.presentation.view.UtilComposables.TypewriterText
import com.app.pokedex.ui.theme.buttonsColor

@Composable
fun PokemonNameDisplay(modifier: Modifier,
                       pokemonName: String,
                       pokemonId: String) {
    val widthDp = LocalConfiguration.current.screenWidthDp
    val heightDp = LocalConfiguration.current.screenHeightDp

    val pressStart2P = FontFamily(
        Font(R.font.press_start_2p)
    )
    Box(
        modifier = modifier
            .offset(x = 30.dp, y = heightDp.dp / 5)
            .width(widthDp.dp / 2)
            .height(120.dp)
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
            text =  if(pokemonName != "null")pokemonName else "",
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 40.dp)
        )
        Text(
            text = if(pokemonId != "null") pokemonId else "",
            color = Color.White,
            fontSize = 17.sp,
            fontFamily = pressStart2P,
            modifier = Modifier.align(Alignment.BottomCenter).offset(y = -25.dp)
        )
    }
}