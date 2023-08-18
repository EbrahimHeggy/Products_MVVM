package dev.ebrahim.movies_mvvm.details.data

import dev.ebrahim.movies_mvvm.details.data.remot.RetrofitClient
import dev.ebrahim.movies_mvvm.details.data.remot.api.ProductApi
import dev.ebrahim.movies_mvvm.details.data.remot.model.Product


class ProductRepo (

    private val productApi: ProductApi = RetrofitClient.productApi
) {


    suspend fun getSpecificProductWithId(productId:Int) : Product {
        return productApi.getProduct(productId)
    }

}