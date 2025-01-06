package com.kishorramani.unittesting.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class CoroutineTestingTest {

    //Whenever any main dispatcher come then use this standard dispatcher
    //It's run all the coroutine in single thread
    private val testMainDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testMainDispatcher)
    }

    @Test
    fun test_coroutine_with_delay() {
        val coroutineTesting = CoroutineTesting()
        /*runBlocking {
            coroutineTesting.getUserName()
        }*/
        runTest {
            coroutineTesting.getUserName()
        }
    }

    @Test
    fun test_coroutine_with_main_thread() {
        //It's coroutine in main thread then use standardTestDispatcher
        val coroutineTesting = CoroutineTesting()
        runTest {
            //get user is in main thread dispatcher
            coroutineTesting.getUser()
        }
    }

    @Test
    fun test_coroutine_with_io_thread() {
        val coroutineTesting = CoroutineTesting(testMainDispatcher)
        runTest {
            //getAddress is in io thread dispatcher
            coroutineTesting.getAddress()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}