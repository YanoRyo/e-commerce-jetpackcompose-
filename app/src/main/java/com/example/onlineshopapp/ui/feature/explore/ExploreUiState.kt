package com.example.onlineshopapp.ui.feature.explore

import com.example.onlineshopapp.domain.model.CategoryModel
import com.example.onlineshopapp.domain.model.ItemsModel

sealed class ExploreUiState {
    data object Loading : ExploreUiState()
    data class Success(
        val bannerUrls: List<String>,
        val categories: List<CategoryModel>,
        val items: List<ItemsModel>
    ) : ExploreUiState()

    data class Error(val message: String) : ExploreUiState()
}