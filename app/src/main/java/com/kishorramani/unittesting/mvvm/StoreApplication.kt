package com.kishorramani.unittesting.mvvm

import android.app.Application
import com.kishorramani.unittesting.mvvm.api.ProductsAPI
import com.kishorramani.unittesting.mvvm.repository.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApplication : Application() {

    lateinit var productsAPI: ProductsAPI
    lateinit var productRepository: ProductRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()

        productsAPI = retrofit.create(ProductsAPI::class.java)
        productRepository = ProductRepository(productsAPI)
    }
}