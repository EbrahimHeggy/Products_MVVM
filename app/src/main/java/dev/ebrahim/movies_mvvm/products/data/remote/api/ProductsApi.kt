package dev.ebrahim.movies_mvvm.products.data.remote.api

import dev.ebrahim.movies_mvvm.products.data.remote.model.news.model.ProductItem
import retrofit2.http.GET

interface ProductsApi {

    companion object{
        const val BASE_URL = "https://fakestoreapi.com/"
    }
    @GET("products")
    suspend fun getProducts () : List<ProductItem>
}

