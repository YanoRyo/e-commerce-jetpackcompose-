package com.example.onlineshopapp.ui.feature.explore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.domain.model.CategoryModel
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.domain.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: ExploreRepository
) : ViewModel() {

    private val _bannerUrls = MutableStateFlow<List<String>>(emptyList())
    val bannerUrls: StateFlow<List<String>> = _bannerUrls

    private val _categories = MutableStateFlow<List<CategoryModel>>(emptyList())
    val categories: StateFlow<List<CategoryModel>> = _categories

    private val _items = MutableStateFlow<List<ItemsModel>>(emptyList())
    val items: StateFlow<List<ItemsModel>> = _items

    init {
        loadBanners()
        loadCategories()
        loadBestSellers()
    }

    private fun loadBanners() {
        viewModelScope.launch {
            repository.loadBanner()
                .catch { e ->
                    // エラー処理
                    println("Error loading banner: ${e.message}")
                }
                .collect { sliderModels ->
                    _bannerUrls.value = sliderModels.map { it.url }
                }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            repository.loadCategory()
                .catch { e ->
                    // エラー処理
                    println("Error loading banner: ${e.message}")
                }
                .collect { categoryModels ->
                    _categories.value = categoryModels.map { it }
                }
        }
    }

    private fun loadBestSellers() {
        viewModelScope.launch {
            repository.loadBestSeller()
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