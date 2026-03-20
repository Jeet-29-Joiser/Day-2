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

        viewModelScope.launch {

            try {

                repository.fetchAndCache(category)

                repository.getProducts(category).collect { products ->

                    _uiState.value =
                        ProductUiState.Success(products)
                }

            } catch (e: Exception) {

                _uiState.value =
                    ProductUiState.Error("Failed to load data")
            }
        }
    }
}