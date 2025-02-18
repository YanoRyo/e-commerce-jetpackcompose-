package com.example.onlineshopapp.ui.feature.detail.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.rememberAsyncImagePainter
import com.example.onlineshopapp.designsystem.theme.LightGray
import com.example.onlineshopapp.ui.feature.detail.component.BackButton
import com.example.onlineshopapp.ui.feature.detail.component.FavoriteButton
import com.example.onlineshopapp.ui.feature.detail.component.ImageThumbnail

@Composable
fun HeaderSection(
    selectedImageUrl: String,
    imageUrls: List<String>,
    onBackClick: () -> Unit,
    onImageSelected: (String) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp)
            .padding(top = 48.dp, bottom = 16.dp)
    ) {
        val (back, favorite, mainImage, thumbnail) = createRefs()
        Image(
            painter = rememberAsyncImagePainter(model = selectedImageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(LightGray, shape = RoundedCornerShape(8.dp))
                .constrainAs(mainImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
        )
        BackButton(
            onBackClick = onBackClick,
            modifier = Modifier.constrainAs(back) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })

        FavoriteButton(
            modifier = Modifier.constrainAs(favorite) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            })

        LazyRow(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .constrainAs(thumbnail) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
//            items(imageUrls){
//                ImageThumbnail(
//                    imageUrl = it,
//                    isSelected = selectedImageUrl == it,
//                    onClick = {
//                        onImageSelected(it)
//                    }
//                )
//            }
            if (imageUrls.isEmpty()) {
                item {
                    ImageThumbnail(
                        imageUrl = "",
                        isSelected = false,
                        onClick = {
                        }
                    )
                    ImageThumbnail(
                        imageUrl = "",
                        isSelected = false,
                        onClick = {
                        }
                    )
                    ImageThumbnail(
                        imageUrl = "",
                        isSelected = false,
                        onClick = {
                        }
                    )
                    ImageThumbnail(
                        imageUrl = "",
                        isSelected = false,
                        onClick = {
                        }
                    )
                }
            } else {
                items(imageUrls) {
                    ImageThumbnail(
                        imageUrl = it,
                        isSelected = selectedImageUrl == it,
                        onClick = {
                            onImageSelected(it)
                        }
                    )
                }
            }
        }
    }
}