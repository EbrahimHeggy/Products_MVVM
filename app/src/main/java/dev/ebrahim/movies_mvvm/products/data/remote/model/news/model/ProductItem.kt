package dev.ebrahim.movies_mvvm.products.data.remote.model.news.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) : Parcelable

@Parcelize
data class Rating (
    val rate : Double,
    val count : Int
): Parcelable
