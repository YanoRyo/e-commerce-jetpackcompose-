package com.example.onlineshopapp.ui.feature.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.onlineshopapp.designsystem.theme.Green
import com.example.onlineshopapp.designsystem.theme.LightGray
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.helper.ManagmentCart

@Composable
fun CartList(
    cartItems: ArrayList<ItemsModel>,
    managmentCart: ManagmentCart,
    onItemChange: () -> Unit = {}
) {

    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
        items(cartItems.size) { index ->
            CartItem(cartItems, index, managmentCart, onItemChange)
        }
    }
}

@Composable
fun CartItem(
    items: ArrayList<ItemsModel>,
    pos: Int,
    managmentCart: ManagmentCart,
    onItemChange: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        val (imageRef, titleRef, feeEachTimeRef, totalEachItemRef, quantityRef) = createRefs()

//        Image(
//            painter = rememberAsyncImagePainter(items[pos].picUrl.firstOrNull()),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(90.dp)
//                .background(
//                    color = LightGray,
//                    shape = RoundedCornerShape(10.dp)
//                )
//                .clip(RoundedCornerShape(10.dp))
//                .constrainAs(imageRef) {
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                }
//        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(items[pos].picUrl.firstOrNull())
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .background(
                    color = LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = items[pos].title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(titleRef) {
                    top.linkTo(imageRef.top)
                    start.linkTo(imageRef.end)
                }
                .padding(start = 8.dp, top = 8.dp)
        )
        Text(
            text = "$${items[pos].price}",
            color = Green,
            modifier = Modifier
                .constrainAs(feeEachTimeRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(titleRef.start)
                }
                .padding(start = 8.dp, top = 8.dp)
        )
//        Text(
//            text = "$${items[pos].price * items[pos].numberInCart}",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .constrainAs(totalEachItemRef) {
//                    start.linkTo(titleRef.end)
//                    bottom.linkTo(imageRef.bottom)
//                }
//                .padding(start = 8.dp)
//        )
        ConstraintLayout(
            modifier = Modifier
                .width(100.dp)
                .constrainAs(quantityRef) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .background(
                    color = LightGray,
                    shape = RoundedCornerShape(100.dp)
                )
        ) {
            val (minusRef, plusRef, numberItemRef) = createRefs()
            Text(
                text = items[pos].numberInCart.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(numberItemRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .background(
                        color = Green,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .constrainAs(plusRef) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        managmentCart.plusItem(
                            items,
                            items.indexOf(items[pos]),
                        ) { onItemChange() }
                    }
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .constrainAs(minusRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        managmentCart.minusItem(
                            items,
                            items.indexOf(items[pos]),
                        ) { onItemChange() }
                    }
            ) {
                Text(
                    text = "-",
                    color = Green,
                    modifier = Modifier
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun CartItemPreview() {
    val context = LocalContext.current
    CartItem(
        items = arrayListOf(ItemsModel.createDummyItem()),
        pos = 0,
        managmentCart = ManagmentCart(context)
    )
}

