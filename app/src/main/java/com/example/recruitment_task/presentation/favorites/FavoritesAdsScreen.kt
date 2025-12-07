package com.example.recruitment_task.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recruitment_task.R
import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.presentation.ads.AdCard
import com.example.recruitment_task.presentation.common.LoadingState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesAdsScreen(
    viewModel: FavoritesAdsViewModel = koinViewModel(),
    onAdClick: (Ad) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.favorites_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (uiState) {
                is FavoritesAdsUIState.Loading -> LoadingState()
                is FavoritesAdsUIState.Success -> FavoritesList(
                    favorites = (uiState as FavoritesAdsUIState.Success).favorites,
                    onAdClick = onAdClick,
                    onFavoriteAdClick = { ad -> viewModel.toggleFavoriteAd(ad) }
                )

                is FavoritesAdsUIState.Empty -> EmptyFavoritesAdsState()
            }
        }
    }
}

@Composable
fun FavoritesList(
    favorites: List<Ad>,
    onAdClick: (Ad) -> Unit,
    onFavoriteAdClick: (Ad) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(dimensionResource(R.dimen.spacing_large)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
    ) {
        items(favorites) { ad ->
            AdCard(
                ad = ad,
                onClick = { onAdClick(ad) },
                isFavorite = true,
                onFavoriteAdClick = { onFavoriteAdClick(ad) })
        }
    }
}

@Composable
fun EmptyFavoritesAdsState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.spacing_large)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_favorites_yet),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))
        Text(
            text = stringResource(R.string.add_items_to_favorites),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}