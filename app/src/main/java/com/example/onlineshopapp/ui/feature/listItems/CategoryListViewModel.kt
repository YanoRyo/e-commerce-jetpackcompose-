package com.example.onlineshopapp.ui.feature.listItems

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.domain.repository.ListItemsReposiroty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val repository: ListItemsReposiroty
): ViewModel() {

    private val _items = MutableStateFlow<List<ItemsModel>>(emptyList())
    val items: StateFlow<List<ItemsModel>> = _items

    private val _itemId = MutableStateFlow<String>("")
    val itemId: StateFlow<String> = _itemId


    fun loadFiltered(id:String) {
        viewModelScope.launch {
            repository.loadFiltered(id)
                .catch { e ->
                    // エラー処理
                    println("Error loading banner: ${e.message}")
                }
                .collect { itemModels ->
                    _items.value = itemModels.map { it }
                    _itemId.value = id
                }
        }
    }
}