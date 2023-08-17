package dev.ebrahim.movies_mvvm.details.data.remot

import dev.ebrahim.movies_mvvm.details.data.remot.api.ProductApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
      private  val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging).build()
            Retrofit.Builder()
                .baseUrl(ProductApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val productApi:ProductApi by lazy {
            retrofit.create(ProductApi::class.java)
        }

    }
}