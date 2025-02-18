package com.example.onlineshopapp.ui.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.domain.model.ItemsModel
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
): ViewModel() {
    private val _items = MutableStateFlow<List<ItemsModel>>(emptyList())
    val items: StateFlow<List<ItemsModel>> = _items

    private val _itemsWithKeys = MutableStateFlow<List<Pair<Int, ItemsModel>>>(emptyList())
    val itemsWithKeys: StateFlow<List<Pair<Int, ItemsModel>>> = _itemsWithKeys

    init {
        loadBestSellerDetail()
    }

    private fun loadBestSellerDetail() {
        viewModelScope.launch {
            repository.loadBestSellerDetail()
                .catch { e ->
                    // エラー処理
                    println("Error loading banner: ${e.message}")
                }
                .collect { itemModels ->
                    _items.value = itemModels.map { it }
                }
        }
    }
}