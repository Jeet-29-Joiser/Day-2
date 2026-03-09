package com.example.itemlist.data

class ProductRepository {

    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Laptop", "$800"),
            Product(2, "Phone", "$500"),
            Product(3, "Headphones", "$150"),
            Product(4, "Keyboard", "$80"),
            Product(5, "Mouse", "$40")
        )
    }

    fun getProductById(id: Int): Product? {
        return getProducts().find { it.id == id }
    }
}