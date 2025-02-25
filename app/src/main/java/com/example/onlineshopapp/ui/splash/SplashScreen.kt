package com.example.onlineshopapp.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshopapp.R
import com.example.onlineshopapp.designsystem.theme.Brown
import com.example.onlineshopapp.designsystem.theme.DarkBrown
import com.example.onlineshopapp.designsystem.theme.Green
import com.example.onlineshopapp.designsystem.theme.OnlineShopAppTheme

@Composable
fun SplashScreen(onClick: () -> Unit = {}) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brown)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Satisfy Your Cravings with Our\n"
                    + "Fresh Cakes And Donuts\n"
                    + "And Pastries",
            color = DarkBrown,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Your Daily Dose of Sweetness",
            color = DarkBrown,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            lineHeight = 30.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(50.dp)
        )
        {
            Text(
                text = "Get Started",
                color = Color.White,
                fontSize = 16.sp,
            )
        }
        Text(
            text = "Already have an account? Sign in",
            color = DarkBrown,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            lineHeight = 30.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    OnlineShopAppTheme {
        SplashScreen(onClick = {})
    }
}
