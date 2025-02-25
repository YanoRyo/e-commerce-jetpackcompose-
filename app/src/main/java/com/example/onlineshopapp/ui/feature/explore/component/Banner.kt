package com.example.onlineshopapp.ui.feature.explore.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.onlineshopapp.designsystem.theme.Brown
import com.example.onlineshopapp.designsystem.theme.DarkBrown
import com.example.onlineshopapp.designsystem.theme.Gray
import com.example.onlineshopapp.designsystem.theme.LightGray
import com.example.onlineshopapp.designsystem.theme.OnlineShopAppTheme

@Composable
fun Banners(bannerUrls: List<String>) {
    AutoSlidingCarousel(banner = bannerUrls)
}


@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier.padding(top = 16.dp),
    banner: List<String>,
    isSelected: Boolean = false,
) {
    // ページ数を指定して rememberPagerState を初期化する
    val pagerState = rememberPagerState(pageCount = { banner.size })

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            beyondViewportPageCount = 1,
            state = pagerState,
        ) { page ->
            val imageUrl = banner[page]
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true) // フェードインアニメーションを追加
                    .build(),
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 8.dp)
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(color = if (isSelected) Brown else LightGray),
            )
        }
        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            totalDots = banner.size,
            selectedIndex = if (isDragged) pagerState.currentPage else pagerState.currentPage,
            dotSize = 8.dp
        )

    }
}


@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = DarkBrown,
    unSelectedColor: Color = Gray,
    dotSize: Dp,
) {
    LazyRow(modifier = modifier.wrapContentSize()) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}


@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    ) {}
}


@Preview
@Composable
fun BannersPreview() {
    OnlineShopAppTheme {
        Banners(bannerUrls = listOf(("https://res.cloudinary.com/drmfoukd6/image/upload/v1739164977/banner1_wfhr7l.png")))
    }
}