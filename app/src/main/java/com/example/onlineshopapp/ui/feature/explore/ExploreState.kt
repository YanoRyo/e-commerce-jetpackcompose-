package com.example.onlineshopapp.ui.feature.explore

import com.example.onlineshopapp.domain.model.SliderModel

data class ExploreState(
    val banners: List<SliderModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
