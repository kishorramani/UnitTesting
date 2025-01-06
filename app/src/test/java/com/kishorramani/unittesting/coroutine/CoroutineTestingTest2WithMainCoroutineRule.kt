package com.kishorramani.unittesting.coroutine

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class CoroutineTestingTest2WithMainCoroutineRule {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun test_coroutine_with_delay() {
        val coroutineTesting = CoroutineTesting(mainCoroutineRule.testMainDispatcher)
        runTest {
            coroutineTesting.getUserName()
        }
    }

    @Test
    fun test_coroutine_with_main_thread() {
        //It's coroutine in main thread then use standardTestDispatcher
        val coroutineTesting = CoroutineTesting(mainCoroutineRule.testMainDispatcher)
        runTest {
            //get user is in main thread dispatcher
            coroutineTesting.getUser()
        }
    }

    @Test
    fun test_coroutine_with_io_thread() {
        val coroutineTesting = CoroutineTesting(mainCoroutineRule.testMainDispatcher)
        runTest {
            //getAddress is in io thread dispatcher
            coroutineTesting.getAddress()
        }
    }

    @Test
    fun testGetAddressDetails() {
        val sut = CoroutineTesting(mainCoroutineRule.testMainDispatcher)
        runTest {
            sut.getAddressDetails()     //here we set globalArg to make it true using coroutine.
            //Assert.assertEquals(true, sut.globalArg)        //It's not work directly

            //Here we tell rule that whatever comes in scheduler, wait until it's done
            //advanceUntilIdle - It's run every coroutine inside the scheduler first
            mainCoroutineRule.testMainDispatcher.scheduler.advanceUntilIdle()

            Assert.assertEquals(true, sut.globalArg)
        }
    }
}