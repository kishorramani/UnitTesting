package com.kishorramani.unittesting.mvvm.api

import com.kishorramani.unittesting.mvvm.models.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductsAPI {
    @GET("/products")
    suspend fun getProducts(): Response<List<ProductListItem>>
}