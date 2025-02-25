package com.example.onlineshopapp.ui.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.domain.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    // UI の状態を保持する MutableStateFlow
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)

    // UI に公開する StateFlow
    val uiState: StateFlow<DetailUiState> = _uiState

    init {
        loadBestSellerDetail()
    }

    private fun loadBestSellerDetail() {
        viewModelScope.launch {
            repository.loadBestSellerDetail()
                .catch { e ->
                    // エラー処理
                    _uiState.value =
                        DetailUiState.Error("Error loading data: ${e.message}")
                }
                .collect { itemModels ->
                    _uiState.value = DetailUiState.Success(itemModels)
                }
        }
    }
}