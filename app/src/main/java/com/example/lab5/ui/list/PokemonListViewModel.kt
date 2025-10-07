package com.example.lab5.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5.data.repository.AppResult
import com.example.lab5.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.lab5.data.model.PokemonListEntry
import com.example.lab5.data.repository.PokemonRepositoryImpl


data class ListUiState(
    val loading: Boolean = false,
    val pokemons: List<PokemonListEntry> = emptyList(),
    val error: String? = null
)

class PokemonListViewModel(
    private val repo: PokemonRepository = PokemonRepositoryImpl()
) : ViewModel() {

    private val _state = MutableStateFlow(ListUiState())
    val state: StateFlow<ListUiState> = _state.asStateFlow()

    fun load() = viewModelScope.launch {
        _state.update { it.copy(loading = true, error = null) }
        when (val res = repo.getPokemonListFirst100()) {
            is AppResult.Success -> _state.update { it.copy(loading = false, pokemons = res.value) }
            is AppResult.Error   -> _state.update { it.copy(loading = false, error = res.message ?: "Error") }
        }
    }
}
