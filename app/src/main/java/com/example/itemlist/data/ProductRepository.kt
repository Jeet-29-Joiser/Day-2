package com.example.itemlist.data

import com.example.itemlist.data.local.ProductDao
import com.example.itemlist.data.mapper.toEntity
import com.example.itemlist.data.mapper.toProduct
import com.example.itemlist.data.network.ProductApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApiService,
    private val dao: ProductDao
) {

    // 🔹 SINGLE SOURCE OF TRUTH (Room)
    fun getProducts(category: String): Flow<List<Product>> {

        return dao.getProductsByCategory(category)
            .map { list -> list.map { it.toProduct() } }
    }

    // 🔹 NETWORK UPDATE (safe)
    suspend fun refreshProducts(category: String) {

        try {

            val products = api.getProductsByCategory(category)

            dao.clearProducts()

            dao.insertProducts(products.map { it.toEntity() })

        } catch (e: Exception) {
            // ❗ IMPORTANT
            // Do nothing → fallback to cached data
        }
    }
}