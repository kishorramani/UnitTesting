package com.kishorramani.unittesting.mvvm.repository

import com.kishorramani.unittesting.mvvm.api.ProductsAPI
import com.kishorramani.unittesting.mvvm.models.ProductListItem
import com.kishorramani.unittesting.mvvm.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProductRepositoryTest {

    @Mock
    lateinit var productAPI: ProductsAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

    }

    @Test
    fun testGetProduct_emptyList() = runTest {
        Mockito.`when`(productAPI.getProducts()).thenReturn(retrofit2.Response.success(emptyList()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)      //It should be return Success
        Assert.assertEquals(0, result.data!!.size)
    }

    @Test
    fun testGetProduct_expectedProductList() = runTest {
        val productList = listOf<ProductListItem>(
            ProductListItem("", "", 1, "", 1.0, "Product 1"),
            ProductListItem("", "", 1, "", 2.0, "Product 2")
        )

        Mockito.`when`(productAPI.getProducts()).thenReturn(retrofit2.Response.success(productList))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Success)      //It should be return Success
        Assert.assertEquals(2, result.data!!.size)
        Assert.assertEquals("Product 1", result.data!![0].title)
    }

    @Test
    fun testGetProduct_expectedError() = runTest {
        Mockito.`when`(productAPI.getProducts()).thenReturn(retrofit2.Response.error(401, "Unauthorized".toResponseBody()))

        val sut = ProductRepository(productAPI)
        val result = sut.getProducts()
        Assert.assertEquals(true, result is NetworkResult.Error)      //It should be return Success
        Assert.assertEquals("Something went wrong", result.message)     //We set, we get messageNetworkResult.Error("Something went wrong")
    }
}