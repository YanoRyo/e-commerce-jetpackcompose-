package com.example.onlineshopapp.ui.feature.explore.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.onlineshopapp.R
import com.example.onlineshopapp.designsystem.theme.DarkBrown
import com.example.onlineshopapp.designsystem.theme.LightGray
import com.example.onlineshopapp.domain.model.ItemsModel


@Composable
fun ItemsList(items: List<ItemsModel>, onItemClick: (Int) -> Unit) {
    LazyRow(
        modifier = Modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(items.size) { index: Int ->
            BestSellerItem(items, items.indexOf(items[index]), onItemClick)
        }
    }
}


@Composable
fun BestSellerItem(
    items: List<ItemsModel>,
    pos: Int,
    onItemClick: (Int) -> Unit
) {
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(180.dp)
    ) {
        AsyncImage(
            model = items[pos].picUrl.firstOrNull(),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .background(LightGray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onItemClick(items[pos].id)
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = items[pos].title,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .width(175.dp)
                .padding(top = 4.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.star), contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = items[pos].rating.toString(),
                    color = Color.Black,
                    fontSize = 14.sp,
                )
            }
        }
        Text(
            text = "$${items[pos].price}",
            color = DarkBrown,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BestSellerItemPreview() {
    val items = listOf(
        ItemsModel(
            id = 1,
            title = "Chocolate cake with cream",
            description = "This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.",
            picUrl = arrayListOf(
                "https://firebasestorage.googleapis.com/v0/b/your-project-id.appspot.com/o/images%2Fchocolate_cake.jpg?alt=media&token=your-token"
            ),
            price = 10.99,
            rating = 4.5,
            size = ArrayList()
        ),
        ItemsModel(
            id = 2,
            title = "Strawberry Shortcake",
            description = "A delicious strawberry shortcake.",
            picUrl = arrayListOf(
                "https://firebasestorage.googleapis.com/v0/b/your-project-id.appspot.com/o/images%2Fstrawberry_shortcake.jpg?alt=media&token=your-token"
            ),
            price = 12.99,
            rating = 4.8,
            size = ArrayList()
        )
    )
    BestSellerItem(items = items, pos = 0, onItemClick = {})
}