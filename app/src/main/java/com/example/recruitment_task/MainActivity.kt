package com.example.recruitment_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.presentation.ads.AdsScreen
import com.example.recruitment_task.presentation.detail.AdDetailScreen
import com.example.recruitment_task.presentation.favorites.FavoritesAdsScreen
import com.example.recruitment_task.ui.theme.RecruitmenttaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecruitmenttaskTheme {
                RecruitmenttaskApp()
            }
        }
    }
}


@Composable
fun RecruitmenttaskApp() {
    var currentDestination by rememberSaveable() { mutableStateOf(AppDestinations.HOME) }
    var selectedAd by rememberSaveable() { mutableStateOf<Ad?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestinations.entries.forEach {
                    item(
                        icon = { Icon(it.icon, contentDescription = it.label) },
                        label = { Text(text = it.label) },
                        selected = it == currentDestination,
                        alwaysShowLabel = true,
                        onClick = {
                            currentDestination = it
                            selectedAd = null
                        }
                    )
                }
            }
        ) {
            selectedAd?.let { ad ->
                AdDetailScreen(
                    ad = ad,
                    onBackClick = {
                        selectedAd = null
                    }
                )
            } ?: when (currentDestination) {
                AppDestinations.HOME -> AdsScreen(
                    onAdClick = { ad ->
                        selectedAd = ad
                    }
                )

                AppDestinations.FAVORITES -> FavoritesAdsScreen(
                    onAdClick = { ad ->
                        selectedAd = ad
                    }
                )
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite)
}