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


    suspend fun refreshProducts(category: String): String? {

        return try {

            val products = api.getProductsByCategory(category)

            dao.clearProducts()

            dao.insertProducts(products.map { it.toEntity() })
            null

        } catch (e: retrofit2.HttpException) {
            when(e.code())
            {
                401 -> "Unauthorizes access"
                404 -> "Products not found"
                500 -> "Server Error"
                else -> "Something Went Wrong"
            }
        }catch (e: java.io.IOException){
            "NO Internet Connedtion"
        }catch (e: Exception)
        {
            "Unexpected error occured"
        }
    }
}