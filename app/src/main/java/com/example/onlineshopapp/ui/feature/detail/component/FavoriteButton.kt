package com.example.onlineshopapp.ui.feature.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.onlineshopapp.R


@Composable
fun FavoriteButton(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.fav_icon),
        contentDescription = null,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    )
}