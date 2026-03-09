package com.example.itemlist.data

import kotlinx.coroutines.delay

class ProductRepository {

    suspend fun getProducts(): List<Product> {
        delay(2000)

            return listOf(
                Product(1, "Laptop", "$800"),
                Product(2, "Phone", "$500"),
                Product(3, "Headphones", "$150"),
                Product(4, "Keyboard", "$80"),
                Product(5, "Mouse", "$40")
            )
        }
    }
