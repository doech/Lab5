package com.example.lab5.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    state: ListUiState,
    onItemClick: (String) -> Unit
) {
    Scaffold(topBar = { TopAppBar(title = { Text("MainFragment") }) }) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {
            when {
                state.loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                state.error != null -> Text(state.error, Modifier.align(Alignment.Center))
                else -> LazyColumn(Modifier.fillMaxSize()) {
                    items(state.pokemons) { p ->
                        Card(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable { onItemClick(p.name.lowercase()) }
                        ) {
                            Row(
                                Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(model = p.imageUrl, contentDescription = p.name, modifier = Modifier.size(48.dp))
                                Spacer(Modifier.width(12.dp))
                                Text(p.name, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}

