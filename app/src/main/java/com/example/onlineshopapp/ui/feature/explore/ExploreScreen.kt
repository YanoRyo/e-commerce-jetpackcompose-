package com.example.onlineshopapp.ui.feature.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopapp.designsystem.theme.OnlineShopAppTheme
import com.example.onlineshopapp.domain.model.CategoryModel
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.ui.feature.explore.section.BannerSection
import com.example.onlineshopapp.ui.feature.explore.section.CategorySection
import com.example.onlineshopapp.ui.feature.explore.section.HeaderSection
import com.example.onlineshopapp.ui.feature.explore.section.PopularProductsSection
import com.example.onlineshopapp.ui.navigation.BottomNavigation

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    onCartClick: () -> Unit,
    onCategoryItemClick: (String, String) -> Unit,
    onItemClick: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ExploreUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ExploreUiState.Success -> {
            val successState = uiState as ExploreUiState.Success
            ExploreContent(
                bannerUrls = successState.bannerUrls,
                categoryItems = successState.categories,
                bestSellerItems = successState.items,
                onCategoryItemClick = onCategoryItemClick,
                onItemClick = onItemClick,
                onCartClick = onCartClick
            )
        }

        is ExploreUiState.Error -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = (uiState as ExploreUiState.Error).message)
            }
        }
    }
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