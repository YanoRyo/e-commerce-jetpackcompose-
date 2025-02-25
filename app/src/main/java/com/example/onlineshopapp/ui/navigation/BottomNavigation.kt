package com.example.onlineshopapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshopapp.R
import com.example.onlineshopapp.designsystem.theme.Green


@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 48.dp)
            .background(Green, shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        BottomItem(
            icon = painterResource(id = R.drawable.btn_1),
            label = "Explore",
            onClick = { }
        )
        BottomItem(
            icon = painterResource(id = R.drawable.btn_2),
            label = "Cart",
            onClick = onCartClick
        )
        BottomItem(
            icon = painterResource(id = R.drawable.btn_3),
            label = "Favorite",
            onClick = { }
        )
        BottomItem(
            icon = painterResource(id = R.drawable.btn_4),
            label = "Orders",
            onClick = { }
        )
        BottomItem(
            icon = painterResource(id = R.drawable.btn_5),
            label = "Profile",
            onClick = { }
        )
    }
}

@Composable
fun BottomItem(
    icon: Painter,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .height(70.dp)
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(icon, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(text = label, color = Color.White, fontSize = 10.sp)

    }
}