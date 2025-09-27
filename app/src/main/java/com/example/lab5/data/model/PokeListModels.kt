package com.example.lab5.data.model

data class PokeListResponse(
    val results: List<PokeListItem>
)

data class PokeListItem(
    val name: String,
    val url: String
)


data class PokemonListEntry(
    val id: Int,
    val name: String,
    val imageUrl: String
)
