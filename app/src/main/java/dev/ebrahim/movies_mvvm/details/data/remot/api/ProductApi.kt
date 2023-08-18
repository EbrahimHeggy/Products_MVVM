package dev.ebrahim.movies_mvvm.details.data.remot.api

import dev.ebrahim.movies_mvvm.details.data.remot.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    companion object {
        const val BASE_URL = "https://fakestoreapi.com/products/"
    }

    @GET("{id}")
    suspend fun getProduct(@Path("id") id: Int): Product

}