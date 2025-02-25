package com.example.onlineshopapp.ui.feature.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopapp.designsystem.theme.OnlineShopAppTheme
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.helper.ManagmentCart
import com.example.onlineshopapp.ui.feature.detail.section.FooterSection
import com.example.onlineshopapp.ui.feature.detail.section.HeaderSection
import com.example.onlineshopapp.ui.feature.detail.section.InfoSection
import com.example.onlineshopapp.ui.feature.detail.section.SizeSelectorSection

@Composable
fun DetailScreen(
    itemId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController,
    onCartClick: () -> Unit
) {
    val context = LocalContext.current
    var itemDetail by remember { mutableStateOf<ItemsModel?>(null) }
    val uiState by viewModel.uiState.collectAsState()

    val managementCart = ManagmentCart(context)

    when (uiState) {
        is DetailUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is DetailUiState.Success -> {
            val items = (uiState as DetailUiState.Success).items
            Log.d("DetailScreen:items", "DetailScreen: $items")

            if (itemId >= 0 && itemId < items.size) {
                itemDetail = items[itemId]
                Log.d("DetailScreen", "DetailScreen: $itemDetail")
            } else {
                Log.d(
                    "DetailScreen",
                    "bestSellerItems is empty or has less than ${itemId + 1} elements"
                )
            }
            if (itemDetail != null) {
                DetailContent(
                    item = itemDetail!!,
                    onBackClick = { navController.popBackStack() },
                    onAddToCartClick = {
                        itemDetail?.numberInCart = 1
                        managementCart.insertItems(itemDetail!!)
                    },
                    onCartClick = {
                        onCartClick()
                    }
                )
            }
        }

        is DetailUiState.Error -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = (uiState as DetailUiState.Error).message)
            }
        }
    }
}

@Composable
fun DetailContent(
    item: ItemsModel,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit
) {

    val defaultImageUrl = "" // デフォルトの画像URLを設定
    var selectedImageUrl by remember {
        mutableStateOf(
            item.picUrl.firstOrNull() ?: defaultImageUrl
        )
    }
    var selectedModelIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderSection(
            selectedImageUrl = selectedImageUrl,
            imageUrls = item.picUrl,
            onBackClick = onBackClick
        ) {
            selectedImageUrl = it
        }

        InfoSection(item = item)

        SizeSelectorSection(
            models = item.size,
            selectedModelIndex = selectedModelIndex,
            onModelSelected = {
                selectedModelIndex = it
            }
        )

        Text(
            text = item.description,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )

        FooterSection(
            onAddToCartClick = onAddToCartClick,
            onCartClick = onCartClick
        )
    }
}

@Preview
@Composable
fun DetailContentPreview() {
    OnlineShopAppTheme {
        DetailContent(
            item = ItemsModel.createDummyItem(),
            onBackClick = {},
            onAddToCartClick = {},
            onCartClick = {}
        )
    }
}




