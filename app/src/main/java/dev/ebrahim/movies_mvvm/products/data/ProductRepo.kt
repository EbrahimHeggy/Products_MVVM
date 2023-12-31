package dev.ebrahim.movies_mvvm.products.data

import dev.ebrahim.movies_mvvm.products.data.remote.RetrofitClient
import dev.ebrahim.movies_mvvm.products.data.remote.api.ProductsApi
import dev.ebrahim.movies_mvvm.products.data.remote.model.news.model.ProductItem

class ProductRepository(
    private val api: ProductsApi = RetrofitClient().productsApi,
) {
    suspend fun getProducts(): List<ProductItem>{
        val result = api.getProducts()
        return result
    }

}