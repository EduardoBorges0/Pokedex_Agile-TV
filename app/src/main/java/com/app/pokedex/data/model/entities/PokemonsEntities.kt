package com.app.pokedex.data.model.entities

data class PokemonsEntities (
    val count : Int,
    val next : String?,
    val previous: String?,
    val results: List<Results>
)

data class Results(
    val name: String,
    val url: String
){
    val id: String
        get() = url.split("/").dropLast(1).last()



    val spriteUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/$id.gif"
}