package com.hollow.imbdfilmes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hollow.imbdfilmes.MovieViewModel
import com.hollow.imbdfilmes.data.MovieDetail

@Composable
fun MovieDetailScreen(
    imdbId: String,
    viewModel: MovieViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(imdbId) {
        viewModel.getMovieDetails(imdbId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(viewModel.movieDetail?.title ?: "Detalhes do Filme") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            viewModel.isLoading -> LoadingIndicator()
            viewModel.errorMessage != null -> ErrorMessage(viewModel.errorMessage!!)
            viewModel.movieDetail != null -> MovieDetailContent(viewModel.movieDetail!!, paddingValues)
            else -> EmptyState("Erro ao carregar detalhes.")
        }
    }
}

@Composable
fun MovieDetailContent(
    detail: MovieDetail,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Pôster e Título
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = detail.poster,
                contentDescription = "Pôster de ${detail.title}",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(150.dp)
                    .height(220.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = detail.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "(${detail.year})",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                RatingChip(rating = detail.imdbRating)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = detail.runtime, style = MaterialTheme.typography.bodyMedium)
                Text(text = detail.genre, style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sinopse
        Text(
            text = "Sinopse",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = detail.plot,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Informações Adicionais
        DetailSection(title = "Diretor", content = detail.director)
        DetailSection(title = "Escritor", content = detail.writer)
        DetailSection(title = "Atores", content = detail.actors)
        DetailSection(title = "Lançamento", content = detail.released)
        DetailSection(title = "Classificação", content = detail.rated)
        DetailSection(title = "Prêmios", content = detail.awards)
    }
}

@Composable
fun DetailSection(title: String, content: String) {
    if (content.isNotBlank() && content != "N/A") {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(
                text = "$title:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun RatingChip(rating: String) {
    if (rating.isNotBlank() && rating != "N/A") {
        AssistChip(
            onClick = { /* Não faz nada */ },
            label = {
                Text(
                    text = "IMDb: $rating/10",
                    fontWeight = FontWeight.Bold
                )
            },
            colors = AssistChipDefaults.assistChipColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                labelColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}
