package com.kishorramani.unittesting.mvvm.repository

import com.kishorramani.unittesting.mvvm.api.ProductsAPI
import com.kishorramani.unittesting.mvvm.models.ProductListItem
import com.kishorramani.unittesting.mvvm.utils.NetworkResult

class ProductRepository(private val productsAPI: ProductsAPI) {

    suspend fun getProducts(): NetworkResult<List<ProductListItem>> {
        val response = productsAPI.getProducts()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error("Something went wrong")
            }
        } else {
            NetworkResult.Error("Something went wrong")
        }
    }
}