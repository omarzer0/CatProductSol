package az.zero.catproductsol.network

import az.zero.catproductsol.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAllProducts(): Response<ProductResponse>

}