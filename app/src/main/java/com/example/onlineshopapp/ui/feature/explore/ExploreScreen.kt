package com.example.onlineshopapp.ui.feature.explore

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopapp.designsystem.theme.OnlineShopAppTheme
import com.example.onlineshopapp.domain.model.CategoryModel
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.navigation.BottomNavigation
import com.example.onlineshopapp.ui.feature.explore.section.BannerSection
import com.example.onlineshopapp.ui.feature.explore.section.CategorySection
import com.example.onlineshopapp.ui.feature.explore.section.HeaderSection
import com.example.onlineshopapp.ui.feature.explore.section.PopularProductsSection

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    onCartClick: () -> Unit,
    onCategoryItemClick: (String, String) -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val bannerUrls by viewModel.bannerUrls.collectAsState()
    val categoryItems by viewModel.categories.collectAsState()
    val bestSellerItems by viewModel.items.collectAsState()

    // リストが空でないことを確認してからアクセスする
    if (bestSellerItems.size >= 2) {
        val bestSellerItems1 = bestSellerItems[1]
        Log.d("ExploreScreen", "ExploreScreen: $bestSellerItems1")
    } else {
        Log.d("ExploreScreen", "bestSellerItems is empty or has less than 2 elements")
    }

    ExploreContent(
        bannerUrls,
        categoryItems,
        bestSellerItems,
        onCategoryItemClick,
        onItemClick,
        onCartClick
    )
}

@Composable
private fun ExploreContent(
    bannerUrls: List<String>,
    categoryItems: List<CategoryModel>,
    bestSellerItems: List<ItemsModel>,
    onCategoryItemClick: (String, String) -> Unit,
    onItemClick: (Int) -> Unit,
    onCartClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (scrollList, bottomMenu) = createRefs()

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .constrainAs(scrollList) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        ) {
            // Header
            item {
                HeaderSection()
            }
            // Banners
            item {
                BannerSection(bannerUrls)
            }
            // Category
            item {
                CategorySection(categoryItems, onCategoryItemClick)
            }
            // Popular Products
            item {
                PopularProductsSection(bestSellerItems, onItemClick)
            }
        }
        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomMenu) {
                    bottom.linkTo(parent.bottom)
                },
            onCartClick = onCartClick
        )
    }
}


@Preview
@Composable
fun ExploreScreenPreview() {
    OnlineShopAppTheme {
        ExploreScreen(
            onCategoryItemClick = { _, _ ->
                // Do nothing, just satisfy the type requirement
            },
            onItemClick = { 0 },
            onCartClick = {}
        )
    }
}