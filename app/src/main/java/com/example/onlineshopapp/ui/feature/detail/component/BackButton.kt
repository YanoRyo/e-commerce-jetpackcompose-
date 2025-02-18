package com.example.onlineshopapp.ui.feature.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.onlineshopapp.R


@Composable
fun BackButton(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.back),
        contentDescription = null,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
            .clickable(onClick = onBackClick)
    )
}