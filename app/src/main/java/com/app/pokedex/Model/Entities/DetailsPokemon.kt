package com.app.pokedex.Model.Entities

data class DetailsPokemon(
    val abilities: List<AbilitySlot>,  // Alterado para List<AbilitySlot> para refletir a nova estrutura
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val types: List<TypeSlot>,
    val weight: Int,
    val location_area_encounters: String
)

data class AbilitySlot(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)

data class Ability(
    val name: String,
    val url: String
)

data class TypeSlot(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)
