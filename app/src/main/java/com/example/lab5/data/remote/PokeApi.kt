package com.example.lab5.data.remote

import com.example.lab5.data.model.PokeListResponse
import com.example.lab5.data.model.PokemonDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): PokeListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(
        @Path("name") nameOrId: String
    ): PokemonDetailsResponse
}
