package com.example.itemlist.data.mapper

import com.example.itemlist.data.Product
import com.example.itemlist.data.local.ProductEntity

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id, title, price, description, category, image
    )
}

fun ProductEntity.toProduct(): Product {
    return Product(
        id, title, price, description, category, image
    )
}