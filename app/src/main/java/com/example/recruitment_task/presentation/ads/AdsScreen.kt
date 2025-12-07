package com.example.recruitment_task.presentation.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.recruitment_task.R
import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.presentation.common.LoadingState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdsScreen(
    viewModel: AdsViewModel = koinViewModel(),
    onAdClick: (Ad) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.marketplace_title)) },
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
                AdsUiState.Loading -> LoadingState()
                is AdsUiState.Success -> AdsList(
                    ads = (uiState as AdsUiState.Success).ads,
                    onAdClick = onAdClick,
                    onFavoriteAdClick = { ad -> viewModel.toggleFavoriteAd(ad) },
                    favorites = favorites
                )

                is AdsUiState.Error -> ErrorState(
                    message = (uiState as AdsUiState.Error).message,
                    onRetry = { viewModel.loadAds() }
                )

                AdsUiState.Empty -> EmptyState()
            }
        }
    }
}

@Composable
fun AdsList(
    ads: List<Ad>,
    onAdClick: (Ad) -> Unit = {},
    onFavoriteAdClick: (Ad) -> Unit,
    favorites: Set<String>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensionResource(R.dimen.grid_min_card_width)),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(dimensionResource(R.dimen.screen_padding)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
    ) {
        items(ads) { ad ->
            AdCard(
                ad = ad,
                onClick = { onAdClick(ad) },
                onFavoriteAdClick = { onFavoriteAdClick(ad) },
                isFavorite = favorites.contains(ad.id)
            )
        }
    }
}

@Composable
fun AdCard(
    ad: Ad,
    onClick: () -> Unit = {},
    onFavoriteAdClick: () -> Unit = {},
    isFavorite: Boolean = false,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.elevation_small)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box {
            Column() {
                Box {
                    AsyncImage(
                        model = ad.image,
                        contentDescription = ad.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(4f / 3f),
                        contentScale = ContentScale.Crop
                    )

                    IconButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(dimensionResource(R.dimen.spacing_small)),
                        onClick = onFavoriteAdClick,
                    ) {
                        Icon(
                            modifier = Modifier.size(dimensionResource(R.dimen.icon_large)),
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFavorite) stringResource(R.string.remove_from_favorites) else stringResource(
                                R.string.add_to_favorites
                            ),
                            tint = if (isFavorite) MaterialTheme.colorScheme.primary else Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(dimensionResource(R.dimen.spacing_small))
                ) {
                    Text(
                        text = ad.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xsmall)))
                    ad.price?.let { price ->
                        Text(
                            text = stringResource(R.string.price_format, price),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary

                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xsmall)))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(dimensionResource(R.dimen.icon_small)),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xsmall)))
                        Text(
                            text = ad.location,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorState(
    message: String,
    onRetry: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.screen_padding)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.error_loading_data),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_large)))
        Button(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.no_ads_available),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}