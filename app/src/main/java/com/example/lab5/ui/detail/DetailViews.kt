// app/src/main/java/com/example/lab5/ui/detail/DetailViews.kt
package com.example.lab5.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab5.data.model.PokemonDetailsResponse

@Composable
fun LoaderView() {
    CircularProgressIndicator()
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Text(text = message)
    Button(onClick = onRetry) { Text("Reintentar") }
}

@Composable
fun PokemonDetailContent(details: PokemonDetailsResponse?, modifier: Modifier = Modifier) {
    if (details == null) return

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Fila 1: Front / Back
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SpriteCell("Front",  details.sprites.frontDefault)
            SpriteCell("Back",   details.sprites.backDefault)
        }
        // Fila 2: Front Shiny / Back Shiny
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SpriteCell("Front Shiny", details.sprites.frontShiny)
            SpriteCell("Back Shiny",  details.sprites.backShiny)
        }
    }
}

@Composable
private fun SpriteCell(label: String, url: String?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.labelSmall)
        AsyncImage(
            model = url,
            contentDescription = label,
            modifier = Modifier.size(112.dp)
        )
    }
}
