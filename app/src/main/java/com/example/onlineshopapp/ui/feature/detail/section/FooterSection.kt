package com.example.onlineshopapp.ui.feature.detail.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshopapp.R
import com.example.onlineshopapp.designsystem.theme.Green
import com.example.onlineshopapp.designsystem.theme.LightGray

@Composable
fun FooterSection(
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, bottom = 48.dp)
    ) {
        Button(
            onClick = onAddToCartClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .padding(end = 8.dp)
        ) {
            Text(
                text = "Add to Cart",
                fontSize = 18.sp,
                color = Color.White
            )
        }
        IconButton(
            onClick = onCartClick,
            modifier = Modifier
                .background(color = LightGray, shape = RoundedCornerShape(10.dp))
                .padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.basket),
                contentDescription = "Cart",
                tint = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun FooterSectionPreview() {

}
