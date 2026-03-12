package com.example.itemlist.viewmodel

import androidx.lifecycle.*
import com.example.itemlist.data.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _uiState = MutableLiveData<ProductUiState>()
    val uiState: LiveData<ProductUiState> = _uiState

    fun loadProducts(category: String) {

        _uiState.value = ProductUiState.Loading

        viewModelScope.launch {

            try {

                val response =
                    repository.getProductsByCategory(category)

                _uiState.value =
                    ProductUiState.Success(response)

            } catch (e: Exception) {

                _uiState.value =
                    ProductUiState.Error("Failed to load products")
            }
        }
    }
}