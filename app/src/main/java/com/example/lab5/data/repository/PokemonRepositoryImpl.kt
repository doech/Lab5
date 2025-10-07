package com.example.lab5.data.repository

import com.example.lab5.data.model.PokemonDetailsResponse
import com.example.lab5.data.model.PokemonListEntry
import com.example.lab5.data.remote.PokeApi
import com.example.lab5.data.remote.RetrofitInstance

class PokemonRepositoryImpl(
    private val api: PokeApi = RetrofitInstance.api
) : PokemonRepository {

    override suspend fun getPokemonListFirst100(): AppResult<List<PokemonListEntry>> = try {
        val res = api.getPokemonList(limit = 100, offset = 0)
        val list = res.results.map { item ->
            val id = item.url.trimEnd('/').split("/").last().toInt()
            PokemonListEntry(
                id = id,
                name = item.name.replaceFirstChar { it.titlecase() },
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
            )
        }
        AppResult.Success(list)
    } catch (t: Throwable) {
        AppResult.Error(NetworkError.from(t), t.message)
    }

    override suspend fun getPokemonDetails(nameOrId: String): AppResult<PokemonDetailsResponse> = try {
        AppResult.Success(api.getPokemonDetails(nameOrId.lowercase()))
    } catch (t: Throwable) {
        AppResult.Error(NetworkError.from(t), t.message)
    }
}

