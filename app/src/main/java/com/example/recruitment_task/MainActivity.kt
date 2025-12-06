package com.example.recruitment_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.recruitment_task.presentation.ads.AdsScreen
import com.example.recruitment_task.ui.theme.RecruitmenttaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecruitmenttaskTheme {
                AdsScreen()
            }
        }
    }
}
