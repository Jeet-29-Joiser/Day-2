package com.example.itemlist.viewmodel

import androidx.lifecycle.*
import com.example.itemlist.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _uiState = MutableLiveData<ProductUiState>()
    val uiState: LiveData<ProductUiState> = _uiState

    fun loadProducts() {

        _uiState.value = ProductUiState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val products = repository.getProducts()

                if (products != null) {
                    _uiState.postValue(ProductUiState.Success(products))
                } else {
                    _uiState.postValue(ProductUiState.Error("Failed to load data"))
                }

            } catch (e: Exception) {

                _uiState.postValue(
                    ProductUiState.Error("No internet connection")
                )
            }
        }
    }
}