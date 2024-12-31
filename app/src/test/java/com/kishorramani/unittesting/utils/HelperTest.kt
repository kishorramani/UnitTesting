package com.kishorramani.unittesting.utils

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class HelperTest {

    private lateinit var helper: Helper

    @Before
    fun setUp() {
        helper = Helper()
        println("Before Every Test Case")
    }

    @Test
    fun isPallindrome() {
        //Arrange
        //val helper = Helper()
        //Act
        val result = helper.isPallindrome("hello")
        //Assert
        assertEquals(false, result)
    }

    @Test
    fun isPallindrome_inputString_level_expectedTrue() {
        //Arrange
        //val helper = Helper()
        //Act
        val result = helper.isPallindrome("level")
        //Assert
        assertEquals(true, result)
    }

    @Test
    fun isPallindrome_inputString_blankString_expectedTrue() {
        //Arrange
        //val helper = Helper()
        //Act
        val result = helper.isPallindrome("")
        //Assert
        assertEquals(true, result)
    }

    @After
    fun tearDown() {
        println("After Every Test Case")
    }
}