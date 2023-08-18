package dev.ebrahim.movies_mvvm.details.peresntation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ebrahim.movies_mvvm.details.data.ProductRepo
import dev.ebrahim.movies_mvvm.details.data.remot.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val productRepo = ProductRepo()

    private val _status = MutableStateFlow(DetailsModel())
    val status = _status.asStateFlow()




     fun getProductWithId(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                val product = productRepo.getSpecificProductWithId(id)
                _status.update {
                    it.copy(product = product)
                }
            }.getOrElse {
                Log.i("error", "not found")
            }
        }

    }
}

data class DetailsModel(
    val product: Product? = null,

)