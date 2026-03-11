package com.example.itemlist.data.network

import com.example.itemlist.data.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(): List<Product>
}