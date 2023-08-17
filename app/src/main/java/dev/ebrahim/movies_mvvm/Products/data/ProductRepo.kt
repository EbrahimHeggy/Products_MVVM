package dev.ebrahim.movies_mvvm.Products.data

import dev.ebrahim.movies_mvvm.Products.data.remote.RetrofitClient
import dev.ebrahim.movies_mvvm.Products.data.remote.api.ProductsApi
import dev.ebrahim.movies_mvvm.Products.data.remote.model.news.model.ProductItem
import dev.ebrahim.movies_mvvm.Products.data.remote.model.news.model.ProductStore

class ProductRepository(
    private val api: ProductsApi = RetrofitClient().productsApi,
) {
    suspend fun getProducts(): List<ProductItem>{
        val result = api.getProducts()
        return result
    }

}