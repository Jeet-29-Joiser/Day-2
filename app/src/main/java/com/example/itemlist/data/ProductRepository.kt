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

    fun getProducts(category: String): Flow<List<Product>> {

        return dao.getProductsByCategory(category)
            .map { list -> list.map { it.toProduct() } }
    }

    suspend fun fetchAndCache(category: String) {

        val products = api.getProductsByCategory(category)

        dao.clearProducts()

        dao.insertProducts(products.map { it.toEntity() })
    }
}