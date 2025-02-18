package com.example.onlineshopapp.ui.feature.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.onlineshopapp.designsystem.theme.Green
import com.example.onlineshopapp.designsystem.theme.LightGray

@Composable
fun ImageThumbnail(
    imageUrl: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backGroundColor = if (isSelected) Green else LightGray

    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(4.dp)
            .then(
                if (isSelected) Modifier.border(
                    width = 1.dp,
                    color = Green,
                    shape = RoundedCornerShape(10.dp)
                ) else Modifier
            )
            .clip(RoundedCornerShape(8.dp))
            .background(backGroundColor, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}