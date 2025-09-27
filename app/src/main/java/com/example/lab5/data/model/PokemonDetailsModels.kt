package com.example.lab5.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailsResponse(
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("back_default")  val backDefault: String?,
    @SerializedName("front_shiny")   val frontShiny: String?,
    @SerializedName("back_shiny")    val backShiny: String?
)
