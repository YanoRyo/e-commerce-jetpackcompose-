package com.example.onlineshopapp.ui.feature.detail.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.onlineshopapp.R
import com.example.onlineshopapp.designsystem.theme.LightGray
import com.example.onlineshopapp.designsystem.theme.OnlineShopAppTheme
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.ui.feature.detail.component.RatingBarRow

@Composable
fun InfoSection(item: ItemsModel) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "¥${item.price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Text(
            text = "Seller",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .background(LightGray, shape = RoundedCornerShape(30.dp))
            )
            Text(
                text = "Jemmy Hanks",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )
            Row {
                Image(
                    painter = painterResource(R.drawable.message),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Image(
                    painter = painterResource(R.drawable.call),
                    contentDescription = null,
                )
            }
        }
        RatingBarRow(rating = item.rating)
    }
}

@Preview
@Composable
fun InfoSectionPreview() {
    OnlineShopAppTheme {
        InfoSection(
            item = ItemsModel(
                title = "Dummy Item 1",
                price = 99.99,
                rating = 4.5,
                imageUrl = arrayListOf(
                    "https://example.com/image1.jpg",
                    "https://example.com/image2.jpg"
                ),
                numberInCart = 0
            )
        )
    }
}