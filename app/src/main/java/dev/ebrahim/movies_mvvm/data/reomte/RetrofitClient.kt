package dev.ebrahim.movies_mvvm.data.reomte

import dev.ebrahim.movies_mvvm.data.reomte.api.ProductsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
       private val retrofit by lazy {

            Retrofit.Builder()
                .baseUrl(ProductsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    val productsApi: ProductsApi by lazy {
        retrofit.create(ProductsApi::class.java)
    }
}