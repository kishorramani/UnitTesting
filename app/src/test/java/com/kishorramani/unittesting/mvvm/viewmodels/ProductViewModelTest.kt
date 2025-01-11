package com.kishorramani.unittesting.mvvm.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kishorramani.unittesting.mvvm.getOrAwaitValue
import com.kishorramani.unittesting.mvvm.models.ProductListItem
import com.kishorramani.unittesting.mvvm.repository.ProductRepository
import com.kishorramani.unittesting.mvvm.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProductViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ProductRepository


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    //to make the function suspend, use runTest
    @Test
    fun test_getProduct() = runTest {
        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Success(emptyList()))     //This is suspend function

        val sut = ProductViewModel(repository)
        sut.getProducts()       //get product
        testDispatcher.scheduler.advanceUntilIdle()     //This wait for the result of coroutine
        val result = sut.products.getOrAwaitValue()
        Assert.assertEquals(0, result.data!!.size)
    }

    @Test
    fun test_getProduct_expectedError() = runTest {
        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Error("Something went wrong"))     //This is suspend function

        val sut = ProductViewModel(repository)
        sut.getProducts()       //get product
        testDispatcher.scheduler.advanceUntilIdle()     //This wait for the result of coroutine
        val result: NetworkResult<List<ProductListItem>> = sut.products.getOrAwaitValue()
        Assert.assertEquals(true, result is NetworkResult.Error)
        Assert.assertEquals("Something went wrong", result.message)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}