package az.zero.catproductsol.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import az.zero.catproductsol.core.MyApp
import az.zero.catproductsol.core.ResponseState
import az.zero.catproductsol.databinding.ActivityMainBinding
import az.zero.catproductsol.ui.adapter.ProductAdapter

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val productAdapter = ProductAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as MyApp).appContainer.productRepository
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        setupViews()
        observeData()

    }

    private fun setupViews() {
        binding.apply {
            rvProducts.adapter = productAdapter
        }
    }

    private fun observeData() {
        viewModel.productLD.observe(this) {
            Log.d(TAG, "observeData: $it")
            when (it) {
                is ResponseState.Error -> {}
                is ResponseState.Loading -> {}
                is ResponseState.Success -> {
                    val products = it.data?.products ?: return@observe
                    productAdapter.submitList(products)
                    Log.d(TAG, "observeData: ${products[0].images[0]}")
                }
            }
        }

        viewModel.categoriesLD.observe(this) {
            when (it) {
                is ResponseState.Error -> {}
                is ResponseState.Loading -> {}
                is ResponseState.Success -> {
                    val categories = it.data ?: return@observe
                }
            }
        }
    }
}