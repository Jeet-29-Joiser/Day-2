package com.example.itemlist.viewmodel

import androidx.lifecycle.*
import com.example.itemlist.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<ProductUiState>()
    val uiState: LiveData<ProductUiState> = _uiState

    fun loadProducts(category: String) {

        _uiState.value = ProductUiState.Loading

        // 🔹 1. Observe DB FIRST (SSOT)
        viewModelScope.launch {

            repository.getProducts(category).collect { products ->

                if (products.isNotEmpty()) {
                    _uiState.value = ProductUiState.Success(products)
                }
            }
        }

        // 🔹 2. Fetch from API in background
        viewModelScope.launch {

            try {
                repository.refreshProducts(category)
            } catch (e: Exception) {

                // Only show error if NO cached data exists
                _uiState.value =
                    ProductUiState.Error("No Internet & No Cached Data")
            }
        }
    }
}