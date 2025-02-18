package com.example.onlineshopapp.ui.feature.cart

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.onlineshopapp.R
import com.example.onlineshopapp.helper.ManagmentCart
import com.example.onlineshopapp.ui.feature.cart.component.CartList
import com.example.onlineshopapp.ui.feature.cart.component.CartSummary

@Composable
fun CartScreen(
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    val managementCart = ManagmentCart(context)
    CartContent(
        managementCart = managementCart,
        onBackClick = onBackClick
    )
}

@SuppressLint("MutableCollectionMutableState")
@Composable
private fun CartContent(
    managementCart: ManagmentCart,
    onBackClick: () -> Unit
) {
    val cartItem = remember { mutableStateOf(managementCart.listCart) }
    val tax = remember { mutableStateOf(0.0) }
    calculatorCart(managementCart, tax)

//    // リストが空でないことを確認してからアクセスする
//    if (cartItem.value.size >= 1) {
//        val CartContent = cartItem.value[1]
//        Log.d("CartContent", "CartContent: $CartContent")
//    } else {
//        Log.d("CartContent", "CartContent is empty or has less than 2 elements")
//    }
    Log.d("CartContent", "CartContent: ${cartItem.value}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ConstraintLayout(modifier = Modifier.padding(top = 36.dp)) {
            val (backBtn, cartTxt) = createRefs()
            Text(
                text = "Your Cart",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(cartTxt) {
                        centerTo(parent)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        onBackClick()
                    }
            )
        }
        if (cartItem.value.isEmpty()) {
            Text(
                text = "Your cart is empty",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            CartList(
                cartItems = cartItem.value,
                managmentCart = managementCart,
            ) {
                cartItem.value = managementCart.listCart
                calculatorCart(managementCart, tax)
            }
            CartSummary(
                itemTotal = managementCart.getTotalFee(),
                tax = tax.value,
                delivery = 10.0
            )
        }
    }

}

private fun calculatorCart(managementCart: ManagmentCart, tax: MutableState<Double>) {
    val percentTax = 0.1
    tax.value = (Math.round(managementCart.getTotalFee() * percentTax) * 100).toDouble() / 100.0
}

@Preview
@Composable
private fun CartContentPreview() {
    CartContent(
        managementCart = ManagmentCart(LocalContext.current),
        onBackClick = {}
    )
}
