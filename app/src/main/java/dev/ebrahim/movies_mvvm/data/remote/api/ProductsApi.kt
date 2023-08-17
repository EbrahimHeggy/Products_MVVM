package dev.ebrahim.movies_mvvm.data.remote.api

import dev.ebrahim.movies_mvvm.data.remote.model.news.model.ProductStore
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    companion object{
        const val BASE_URL = "https://fakestoreapi.com/"
    }
    @GET("products")
    suspend fun getProducts () : Response<ProductStore>
}

