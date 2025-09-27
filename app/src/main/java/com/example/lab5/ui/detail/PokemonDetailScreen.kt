package com.example.lab5.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    state: DetailUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DetailFragment") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {
            when {
                state.loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                state.error != null -> Text(state.error, Modifier.align(Alignment.Center))
                else -> {
                    val s = state.sprites
                    if (s == null) {
                        Text("Sin datos", Modifier.align(Alignment.Center))
                    } else {
                        Column(Modifier.fillMaxWidth().padding(16.dp)) {
                            Text(state.name, style = MaterialTheme.typography.titleLarge)
                            Spacer(Modifier.height(12.dp))

                            val items = listOf(
                                "Front" to s.frontDefault,
                                "Back" to s.backDefault,
                                "Front Shiny" to s.frontShiny,
                                "Back Shiny" to s.backShiny
                            )

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(items) { (label, url) ->
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(label, style = MaterialTheme.typography.bodyMedium)
                                        if (url != null) {
                                            AsyncImage(model = url, contentDescription = label, modifier = Modifier.size(120.dp))
                                        } else {
                                            Box(Modifier.size(120.dp), contentAlignment = Alignment.Center) { Text("N/A") }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
