package com.kishorramani.unittesting.mockwebserver

import com.kishorramani.unittesting.mvvm.api.ProductsAPI
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPITest {
    lateinit var mockWebServer: MockWebServer
    lateinit var productAPI: ProductsAPI

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        productAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductsAPI::class.java)
    }

    @Test
    fun testGetProduct() = runTest {
        //Arrange - set server response for request
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        //Act - send request
        val response = productAPI.getProducts()
        mockWebServer.takeRequest()

        //Assert - check response
        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @Test
    fun testGetProduct_returnProducts() = runTest {
        //Arrange - set server response for request
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/product_response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        //Act - send request
        val response = productAPI.getProducts()
        mockWebServer.takeRequest()

        //Assert - check response
        Assert.assertEquals(false, response.body()!!.isEmpty())
        Assert.assertEquals(2, response.body()!!.size)
    }

    @Test
    fun testGetProduct_returnError() = runTest {
        //Arrange - set server response for request
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        //Act - send request
        val response = productAPI.getProducts()
        mockWebServer.takeRequest()

        //Assert - check response
        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}