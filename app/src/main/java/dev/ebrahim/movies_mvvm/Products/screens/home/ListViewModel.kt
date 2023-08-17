package dev.ebrahim.movies_mvvm.Products.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ebrahim.movies_mvvm.Products.data.ProductRepository
import dev.ebrahim.movies_mvvm.Products.data.remote.model.news.model.ProductItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private val productRepository = ProductRepository()
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            kotlin.runCatching {
                val productList = productRepository.getProducts()
                Log.d("API_CALL", "Product list size: ${productList.size}")
                _state.update {
                    it.copy(isLoading = false, list = productList)
                }
            }.getOrElse { error ->
                Log.e("API_CALL", "API call failed: ${error.message}")
                _state.update {
                    it.copy(
                        isLoading = false,
                        dialogModel = DialogModel(error.message.toString(), true)
                    )
                }
            }
        }
    }




    fun dismissDialog() {
        _state.update {
            it.copy(dialogModel = null)
        }
    }

}


data class HomeState(
    val isLoading: Boolean = true,
    val list: List<ProductItem> = emptyList(),
    val dialogModel: DialogModel? = null
)

data class DialogModel(
    val message: String,
    val isShouldShow: Boolean = false
)