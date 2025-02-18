package com.example.onlineshopapp.ui.feature.listItems.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.ui.feature.explore.component.BestSellerItem

@Composable
fun ItemsListFullSizeVertical(items: List<ItemsModel>, onItemClick: (Int) -> Unit) {
    LazyVerticalGrid (
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items.size) { index: Int ->
            BestSellerItem(items, index, onItemClick)
        }
    }
}