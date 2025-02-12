package com.app.pokedex.presentation.view.SearchPokemons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.app.pokedex.R
import com.app.pokedex.presentation.viewmodel.PokemonsViewModel
import com.app.pokedex.ui.theme.buttonsColor

@Composable
fun TextFieldSearchPokemon(pokemonsViewModel: PokemonsViewModel) {
    val searchPokemons by pokemonsViewModel.searchResults.observeAsState()
    val getAllPokemonsId = pokemonsViewModel.getAllPokemonsId

    var inputText = remember { mutableStateOf("") }
    val widthDp = LocalConfiguration.current.screenWidthDp

    Box(modifier = Modifier.fillMaxSize()){
        TextField(
            value = inputText.value,
            onValueChange = {
                inputText.value = it
                pokemonsViewModel.searchPokemons(it)
                if(pokemonsViewModel.getAllPokemonsId >= (searchPokemons?.size ?: 0) && (searchPokemons?.size
                        ?: 0) != 0
                ){
                    pokemonsViewModel.getAllPokemonsId = searchPokemons?.size?.minus(1) ?: 0
                }
                if(searchPokemons?.isNotEmpty() == true){
                    pokemonsViewModel.getSearchPokemonsId = searchPokemons?.get(getAllPokemonsId)?.id?.toInt() ?: 0
                }
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(widthDp.dp - 70.dp)
                .padding(top = 22.dp, start = widthDp.dp / 6)
                .clip(RoundedCornerShape(8.dp)),
            label = {
                Text("Qual pokémon você quer?", fontFamily = FontFamily(Font(R.font.press_start_2p)))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = buttonsColor,
                unfocusedContainerColor = buttonsColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor =  Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
            ),
            textStyle = TextStyle(
                fontFamily = FontFamily(Font(R.font.press_start_2p)),
            ),
        )
    }

}