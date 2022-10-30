package az.zero.catproductsol.core

import az.zero.catproductsol.network.ApiService
import az.zero.catproductsol.repository.ProductRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        client(client)
        addConverterFactory(GsonConverterFactory.create())
    }.build()


    private val apiService = retrofit.create(ApiService::class.java)

    val productRepository = ProductRepository(apiService)
}