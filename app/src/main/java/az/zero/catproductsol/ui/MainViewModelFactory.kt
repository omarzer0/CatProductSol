package az.zero.catproductsol.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import az.zero.catproductsol.repository.ProductRepository

class MainViewModelFactory constructor(private val productRepository: ProductRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(productRepository) as T
    }
}