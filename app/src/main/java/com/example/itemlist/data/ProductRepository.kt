package com.example.itemlist.data

import com.example.itemlist.data.network.RetrofitClient

class ProductRepository {

    suspend fun getProducts(): List<Product> {
        return RetrofitClient.api.getProducts()
    }

    suspend fun getProductsByCategory(category: String): List<Product> {
        return RetrofitClient.api.getProductsByCategory(category)
    }
}