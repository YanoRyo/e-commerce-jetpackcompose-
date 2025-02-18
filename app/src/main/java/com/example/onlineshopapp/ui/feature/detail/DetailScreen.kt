package com.example.onlineshopapp.ui.feature.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var itemDetail = ItemsModel()
    val managementCart = ManagmentCart(context)

    val items by viewModel.items.collectAsState()

    // リストが空でないことを確認してからアクセスする
    if (items.size >= 2) {
        itemDetail = items[itemId]
        Log.d("DetailScreen", "DetailScreen: $itemDetail")
    } else {
        Log.d("DetailScreen", "bestSellerItems is empty or has less than 2 elements")
    }



    DetailContent(
        item = itemDetail,
        onBackClick = { navController.popBackStack() },
        onAddToCartClick = {
            itemDetail.numberInCart = 1
            managementCart.insertItems(itemDetail)
        },
        onCartClick = {
            onCartClick()
        }
    )
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun DetailContent(
    item: ItemsModel,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit
) {

//    var selectedImageUrl by remember { mutableStateOf(item.imageUrl.first()) }
//    var selectedModelIndex by remember { mutableStateOf(-1) }
    val firstImageUrl = item.imageUrl.firstOrNull()
    var selectedImageUrl by remember { mutableStateOf(firstImageUrl ?: "") }
    var selectedModelIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderSection(
            selectedImageUrl = selectedImageUrl,
            imageUrls = item.imageUrl,
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




