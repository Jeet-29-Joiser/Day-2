package com.example.itemlist.data

import com.example.itemlist.data.network.RetrofitClient

class ProductRepository {

    suspend fun getProducts(): List<Product>? {

        val response = RetrofitClient.api.getProducts()

        if (response.isSuccessful) {
            return response.body()
        }

        return null
    }
}