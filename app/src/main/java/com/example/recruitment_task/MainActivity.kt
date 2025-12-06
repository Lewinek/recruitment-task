package com.example.recruitment_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.presentation.ads.AdsScreen
import com.example.recruitment_task.presentation.detail.AdDetailScreen
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

    var selectedAd by rememberSaveable() { mutableStateOf<Ad?>(null) }

    selectedAd?.let { ad ->
        AdDetailScreen(
            ad = ad,
            onBackClick = { selectedAd = ad }
        )
    } ?: AdsScreen(
        onAdClick = { ad -> selectedAd = ad }
    )
}