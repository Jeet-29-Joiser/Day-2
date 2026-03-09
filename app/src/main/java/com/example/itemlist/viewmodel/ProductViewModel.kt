package com.example.itemlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itemlist.data.Product
import com.example.itemlist.data.ProductRepository

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun loadProducts() {
        _products.value = repository.getProducts()
    }

    fun getProductById(id: Int): Product? {
        return repository.getProductById(id)
    }
}