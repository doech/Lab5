package com.example.lab5.data.repository

import com.example.lab5.data.model.PokemonDetailsResponse
import com.example.lab5.data.model.PokemonListEntry
import com.example.lab5.data.repository.AppResult

interface PokemonRepository {
    suspend fun getPokemonListFirst100(): AppResult<List<PokemonListEntry>>
    suspend fun getPokemonDetails(nameOrId: String): AppResult<PokemonDetailsResponse>
}
