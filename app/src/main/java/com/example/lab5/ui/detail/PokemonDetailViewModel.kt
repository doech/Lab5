package com.example.lab5.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5.data.model.Sprites
import com.example.lab5.data.repository.PokemonRepository
import kotlinx.coroutines.launch

data class DetailUiState(
    val loading: Boolean = false,
    val name: String = "",
    val sprites: Sprites? = null,
    val error: String? = null
)

class PokemonDetailViewModel(
    private val repo: PokemonRepository = PokemonRepository()
) : ViewModel() {

    var state by mutableStateOf(DetailUiState())
        private set

    fun load(name: String) {
        viewModelScope.launch {
            state = state.copy(loading = true, error = null)
            try {
                val details = repo.getPokemonDetails(name)
                state = state.copy(
                    loading = false,
                    name = name.replaceFirstChar { it.titlecase() },
                    sprites = details.sprites
                )
            } catch (e: Exception) {
                state = state.copy(loading = false, error = e.message ?: "Error desconocido")
            }
        }
    }
}
