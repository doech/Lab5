package com.example.lab5.ui.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    nameOrId: String,
    vm: PokemonDetailViewModel = viewModel(),
    onBack: () -> Unit = {}
) {
    val state by vm.state.collectAsStateWithLifecycle()
    LaunchedEffect(nameOrId) { vm.load(nameOrId) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DetailFragment") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { inner ->
        when {
            state.loading      -> LoaderView()
            state.error != null-> ErrorView(state.error!!) { vm.load(nameOrId) }
            else               -> PokemonDetailContent(state.details, Modifier.padding(inner))
        }
    }
}
