package az.zero.catproductsol.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.zero.catproductsol.core.ResponseState
import az.zero.catproductsol.core.networkCall
import az.zero.catproductsol.models.ProductResponse
import az.zero.catproductsol.repository.ProductRepository
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel(
    private val repository: ProductRepository,
) : ViewModel() {

    private val _productMLD = MutableLiveData<ResponseState<ProductResponse>>()
    val productLD: LiveData<ResponseState<ProductResponse>> = _productMLD

    private fun getAllProducts() {
        viewModelScope.launch {
            networkCall(
                action = { repository.getAllProducts() },
                onResponse = { _productMLD.postValue(it) }
            )
        }
    }

    init {
        getAllProducts()
    }
}
