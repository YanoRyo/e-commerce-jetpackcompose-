package com.example.onlineshopapp.ui.feature.explore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.domain.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: ExploreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExploreUiState>(ExploreUiState.Loading)
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val bannerUrls =
                    repository.loadBanner().firstOrNull()?.map { it.url } ?: emptyList()
                val categories = repository.loadCategory().firstOrNull() ?: emptyList()
                val items = repository.loadBestSeller().firstOrNull() ?: emptyList()
                _uiState.value = ExploreUiState.Success(bannerUrls, categories, items)
            } catch (e: Exception) {
                Log.e("ExploreViewModel", "Error loading data: ${e.message}")
                _uiState.value = ExploreUiState.Error("Failed to load data: ${e.message}")
            }
        }
    }
}