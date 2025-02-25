package com.example.onlineshopapp.ui.feature.explore.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.onlineshopapp.designsystem.theme.Brown
import com.example.onlineshopapp.designsystem.theme.DarkBrown
import com.example.onlineshopapp.designsystem.theme.LightGray
import com.example.onlineshopapp.designsystem.theme.OnlineShopAppTheme
import com.example.onlineshopapp.domain.model.CategoryModel

@Composable
fun CategoryList(
    categories: List<CategoryModel>,
    onCategoryItemClick: (String, String) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val context = LocalContext.current
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)
    ) {
        items(categories.size) { index ->
            CategoryItem(
                item = categories[index],
                isSelected = index == selectedIndex,
                onItemClick = {
                    selectedIndex = index
                    onCategoryItemClick(categories[index].id.toString(), categories[index].title)
                }
            )
        }
    }
}


@Composable
fun CategoryItem(
    item: CategoryModel,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable { onItemClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.picUrl)
                .crossfade(true) // フェードインアニメーションを追加
                .build(),
            contentDescription = item.title,
            modifier = Modifier
                .size(if (isSelected) 60.dp else 50.dp)
                .background(
                    color = if (isSelected) Brown else LightGray,
                    shape = RoundedCornerShape(100.dp)
                ),
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = item.title,
            color = DarkBrown,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
fun CategoryListPreview() {
    OnlineShopAppTheme {
        CategoryItem(
            item = CategoryModel(
                id = 1,
                title = "test",
                picUrl = ""
            ),
            isSelected = true,
            onItemClick = {}
        )
    }
}