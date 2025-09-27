package com.example.lab5.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5.data.model.PokemonListEntry
import com.example.lab5.data.repository.PokemonRepository
import kotlinx.coroutines.launch

data class ListUiState(
    val loading: Boolean = false,
    val pokemons: List<PokemonListEntry> = emptyList(),
    val error: String? = null
)

class PokemonListViewModel(
    private val repo: PokemonRepository = PokemonRepository()
) : ViewModel() {

    var state by mutableStateOf(ListUiState())
        private set

    init { load() }

    fun load() {
        viewModelScope.launch {
            state = state.copy(loading = true, error = null)
            try {
                val list = repo.getPokemonListFirst100()
                state = state.copy(loading = false, pokemons = list)
            } catch (e: Exception) {
                state = state.copy(loading = false, error = e.message ?: "Error desconocido")
            }
        }
    }
}
