package com.example.onlineshopapp.ui.feature.listItems

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopapp.R
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.ui.feature.listItems.component.ItemsListFullSizeVertical

@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel = hiltViewModel(),
    navController: NavHostController,
    categoryId: String,
    title: String,
    onItemClick: (Int) -> Unit,
) {
    val itemId by viewModel.itemId.collectAsState()
    val items by viewModel.items.collectAsState()
    var isLoading by remember { mutableStateOf(true) }

    // LaunchedEffect を使用して、id が変更されたときに loadFiltered を呼び出す
    LaunchedEffect(key1 = categoryId) {
        viewModel.loadFiltered(categoryId)
    }

    LaunchedEffect(key1 = items) {
        isLoading = items.isEmpty()
    }

    ListItemsContent(
        viewModel = viewModel,
        categoryId = categoryId,
        title = title,
        items = items,
        isLoading = isLoading,
        onItemClick = onItemClick,
        onBackClick = { navController.popBackStack() }
    )

}

@Composable
private fun ListItemsContent(
    viewModel: CategoryListViewModel,
    categoryId: String,
    title: String,
    items: List<ItemsModel>,
    isLoading: Boolean,
    onItemClick: (Int) -> Unit,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 36.dp)
        ) {
            val (backBtn, cartTxt) = createRefs()
            Text(
                text = title,
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
                    .clickable {
                        onBackClick()
                    }
                    .constrainAs(backBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)

                    }
            )
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

            }
        } else {
            ItemsListFullSizeVertical(
                items = items,
                onItemClick = { index ->
                    onItemClick(index)
                }
            )
        }
    }
}