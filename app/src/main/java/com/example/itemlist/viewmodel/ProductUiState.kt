package com.example.itemlist.viewmodel

import android.os.Message
import com.example.itemlist.data.Product

sealed class ProductUiState {

    object Loading : ProductUiState()
    data class Success(val products: List<Product>): ProductUiState()
    data class Error(val message: String): ProductUiState()

}