package com.example.onlineshopapp.ui.feature.explore.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshopapp.domain.model.CategoryModel
import com.example.onlineshopapp.ui.feature.explore.component.CategoryList


@Composable
fun CategorySection(
    categoryItems: List<CategoryModel>,
    onCategoryItemClick: (String, String) -> Unit
) {
    Text(
        "Categories",
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
    )

    if (categoryItems.isEmpty()) {
        // Loading indicator
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(50.dp),
            contentAlignment = Alignment.Center

        ) {
            CircularProgressIndicator()
        }
    } else {
        CategoryList(
            categories = categoryItems,
            onCategoryItemClick = { clickedCategoryId, clickedCategoryTitle ->
                onCategoryItemClick(clickedCategoryId, clickedCategoryTitle)
            }
        )
    }
}