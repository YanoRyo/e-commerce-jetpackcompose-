package com.example.onlineshopapp.ui.feature.explore.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.onlineshopapp.ui.feature.explore.component.Banners

@Composable
fun BannerSection(bannerUrls: List<String>) {
    if (bannerUrls.isEmpty()) {
        // Loading indicator
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center

        ) {
            CircularProgressIndicator()
        }
    } else {
        Banners(bannerUrls = bannerUrls)
    }
}