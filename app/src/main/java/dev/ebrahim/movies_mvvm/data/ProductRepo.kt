package dev.ebrahim.movies_mvvm.data

import dev.ebrahim.movies_mvvm.data.reomte.RetrofitClient
import dev.ebrahim.movies_mvvm.data.reomte.api.ProductsApi
import dev.ebrahim.movies_mvvm.data.reomte.model.news.model.ProductItem

class ProductRepository(
    private val api: ProductsApi = RetrofitClient().productsApi,
) {
    suspend fun getProducts(): List<ProductItem> {
        val result = api.getProducts()
        return result.body()?.products.orEmpty()
    }
}