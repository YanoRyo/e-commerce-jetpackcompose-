package com.example.onlineshopapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlineshopapp.ui.feature.cart.CartScreen
import com.example.onlineshopapp.ui.feature.detail.DetailScreen
import com.example.onlineshopapp.ui.feature.explore.ExploreScreen
import com.example.onlineshopapp.ui.feature.listItems.CategoryListScreen
import com.example.onlineshopapp.ui.feature.searchlist.SearchListScreen
import com.example.onlineshopapp.ui.feature.splash.SplashScreen

@Composable
fun EcommerceApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreen.SplashScreen.route) {
        composable(MainScreen.SplashScreen.route) {
            SplashScreen {
                navController.navigate(MainScreen.ExploreScreen.route)
            }
        }
        composable(MainScreen.ExploreScreen.route) {
            ExploreScreen(
                onCategoryItemClick = { categoryId, categoryTitle ->
                    navController.navigate(
                        MainScreen.CategoryList.getCategoryIdAndTitle(
                            categoryId,
                            categoryTitle
                        )
                    )
                },
                onItemClick = { itemId ->
                    navController.navigate(MainScreen.ItemDetail.getItemId(itemId))
                },
                onCartClick = {
                    navController.navigate(MainScreen.CartScreen.route)
                }
            )
        }
        composable(
            MainScreen.CategoryListScreen.route,
            arguments = listOf(
                navArgument(MainScreen.CategoryList.CATEGORY_ID) {
                    type = NavType.StringType
                },
                navArgument(MainScreen.CategoryList.CATEGORY_TITLE) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val categoryId =
                navBackStackEntry.arguments?.getString(MainScreen.CategoryList.CATEGORY_ID)
            val categoryTitle =
                navBackStackEntry.arguments?.getString(MainScreen.CategoryList.CATEGORY_TITLE) ?: ""
            categoryId?.let {
                CategoryListScreen(
                    categoryId = categoryId,
                    title = categoryTitle,
                    navController = navController,
                    onItemClick = { itemId ->
                        navController.navigate(MainScreen.ItemDetail.getItemId(itemId))
                    }
                )
            }
        }
        composable(
            MainScreen.DetailScreen.route,
            arguments = listOf(navArgument(MainScreen.ItemDetail.ITEM_ID) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getInt(MainScreen.ItemDetail.ITEM_ID)
            itemId?.let {
                DetailScreen(
                    itemId = it,
                    navController = navController,
                    onCartClick = {
                        navController.navigate(MainScreen.CartScreen.route)
                    }
                )
            }
        }
        composable(MainScreen.CartScreen.route) { CartScreen(onBackClick = { navController.popBackStack() }) }
        composable(MainScreen.SearchListScreen.route) { SearchListScreen() }
    }
}

sealed class MainScreen(val route: String) {
    data object SplashScreen : MainScreen(SPLASH_SCREEN)
    data object SearchListScreen : MainScreen(SEARCH_LIST_SCREEN)
    data object ExploreScreen : MainScreen(EXPLORE_SCREEN)
    data object CartScreen : MainScreen(CART_SCREEN)
    data object CategoryListScreen : MainScreen(CATEGORY_LIST_SCREEN)
    data class CategoryList(val categoryId: String) :
        MainScreen("$CATEGORY_LIST_SCREEN/{$CATEGORY_ID}/{$CATEGORY_TITLE}") {
        companion object {
            const val CATEGORY_ID = "categoryId"
            const val CATEGORY_TITLE = "categoryTitle"
            const val CATEGORY_LIST_SCREEN = "category_list_screen"
            fun getCategoryIdAndTitle(categoryId: String, title: String) =
                "$CATEGORY_LIST_SCREEN/$categoryId/$title"
        }
    }

    data object DetailScreen : MainScreen(DETAIL_SCREEN)
    data class ItemDetail(val itemId: Int) : MainScreen("$DETAIL_SCREEN/{$ITEM_ID}") {
        companion object {
            const val ITEM_ID = "itemId"
            const val DETAIL_SCREEN = "detail_screen"
            fun getItemId(itemId: Int) = "$DETAIL_SCREEN/$itemId"
        }
    }

    companion object {
        private const val SPLASH_SCREEN = "splash_screen"
        private const val SEARCH_LIST_SCREEN = "search_list_screen"
        private const val EXPLORE_SCREEN = "explore_screen"
        private const val CART_SCREEN = "cart_screen"
        private const val CATEGORY_LIST_SCREEN = "category_list_screen/{categoryId}/{categoryTitle}"
        private const val DETAIL_SCREEN = "detail_screen/{itemId}"
    }
}


