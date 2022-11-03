package az.zero.catproductsol.repository

import az.zero.catproductsol.network.ApiService

class ProductRepository(
    private val api: ApiService,
) {

    suspend fun getAllProducts() = api.getAllProducts()

}