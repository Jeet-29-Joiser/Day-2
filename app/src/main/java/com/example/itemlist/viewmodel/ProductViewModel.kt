package com.example.itemlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itemlist.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _uiState = MutableLiveData<ProductUiState>()
    val uiState: LiveData<ProductUiState> = _uiState

    fun loadProducts() {

        _uiState.value = ProductUiState.Loading

        viewModelScope.launch {

            try {

                val products = withContext(Dispatchers.IO) {
                    repository.getProducts()
                }

                _uiState.value = ProductUiState.Success(products)

            } catch (e: Exception) {

                _uiState.value =
                    ProductUiState.Error("Failed to load products")
            }
        }
    }
}