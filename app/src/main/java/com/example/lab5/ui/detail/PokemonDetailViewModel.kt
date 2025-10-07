package com.example.lab5.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab5.data.repository.AppResult
import com.example.lab5.data.model.PokemonDetailsResponse
import com.example.lab5.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.lab5.data.repository.PokemonRepositoryImpl
data class DetailUiState(
    val loading: Boolean = false,
    val details: PokemonDetailsResponse? = null,
    val error: String? = null
)

class PokemonDetailViewModel(
    private val repo: PokemonRepository = PokemonRepositoryImpl()
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState())
    val state: StateFlow<DetailUiState> = _state.asStateFlow()

    fun load(nameOrId: String) = viewModelScope.launch {
        _state.update { it.copy(loading = true, error = null) }
        when (val res = repo.getPokemonDetails(nameOrId)) {
            is AppResult.Success -> _state.update { it.copy(loading = false, details = res.value) }
            is AppResult.Error   -> _state.update { it.copy(loading = false, error = res.message ?: "Error") }
        }
    }
}