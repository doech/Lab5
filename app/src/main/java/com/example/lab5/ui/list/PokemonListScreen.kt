package com.example.lab5.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab5.ui.detail.ErrorView
import com.example.lab5.ui.detail.LoaderView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    vm: PokemonListViewModel = viewModel(),
    onOpenDetail: (String) -> Unit
) {
    val state by vm.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { vm.load() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MainFragment") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { inner ->
        when {
            state.loading      -> LoaderView()
            state.error != null-> ErrorView(state.error!!) { vm.load() }
            else               -> PokemonListContent(
                items = state.pokemons,
                onOpenDetail = onOpenDetail,
                modifier = Modifier.padding(inner)
            )
        }
    }
}
