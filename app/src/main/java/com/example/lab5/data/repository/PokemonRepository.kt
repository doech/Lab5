package com.example.lab5.data.repository

import com.example.lab5.data.model.PokemonListEntry
import com.example.lab5.data.remote.RetrofitInstance

class PokemonRepository {
    private val api = RetrofitInstance.api

    suspend fun getPokemonListFirst100(): List<PokemonListEntry> {
        val res = api.getPokemonList(limit = 100, offset = 0)
        return res.results.map { item ->
            // URL: https://pokeapi.co/api/v2/pokemon/1/
            val id = item.url.trimEnd('/').split("/").last().toInt()
            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
            PokemonListEntry(
                id = id,
                name = item.name.replaceFirstChar { it.titlecase() },
                imageUrl = imageUrl
            )
        }
    }

    suspend fun getPokemonDetails(nameOrId: String) =
        api.getPokemonDetails(nameOrId.lowercase())
}
