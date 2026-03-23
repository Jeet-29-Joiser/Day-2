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

        // 1️⃣ Always observe DB
        viewModelScope.launch {

            repository.getProducts(category).collect { products ->

                if (products.isNotEmpty()) {
                    _uiState.value = ProductUiState.Success(products)
                }
            }
        }

        // 2️⃣ Try API
        viewModelScope.launch {

            val error = repository.refreshProducts(category)

            if (error != null) {

                _uiState.value =
                    ProductUiState.Error(error)
            }
        }
    }
}