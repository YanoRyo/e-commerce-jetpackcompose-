package com.example.onlineshopapp.ui.feature.detail

import com.example.onlineshopapp.domain.model.ItemsModel

// UI の状態を表す sealed class
sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Success(val items: List<ItemsModel>) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}