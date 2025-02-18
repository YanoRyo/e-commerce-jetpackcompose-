package com.example.onlineshopapp.ui.feature.detail.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshopapp.designsystem.theme.Green
import com.example.onlineshopapp.designsystem.theme.LightGray
import com.example.onlineshopapp.designsystem.theme.OnlineShopAppTheme

@Composable
fun SizeSelectorSection(
    models: List<String>,
    selectedModelIndex: Int,
    onModelSelected: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Select Weight",
            fontWeight = FontWeight.Bold,
        )
        LazyRow(
            modifier = Modifier
                .padding(vertical = 10.dp),
        ) {
            itemsIndexed(models) { index, model ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .padding(end = 4.dp)
                        .height(40.dp)
                        .background(
                            if (index == selectedModelIndex) Green else LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { onModelSelected(index) }
                ) {
                    Text(
                        text = model,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = if (index == selectedModelIndex) Color.White else Color.Black,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ModelSelectorPreview() {
    OnlineShopAppTheme {
        SizeSelectorSection(
            models = listOf("1kg", "2kg", "3kg"),
            selectedModelIndex = 1,
            onModelSelected = {}
        )
    }
}