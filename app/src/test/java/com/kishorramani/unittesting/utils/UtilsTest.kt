package com.kishorramani.unittesting.utils

import org.junit.Assert.*

import org.junit.Test

class UtilsTest {

    @Test
    fun validatePassword_blankInput_expectedRequiredField() {
        //arrange
        val sut = Utils()       //sut means - System Under Test
        //act
        val result = sut.validatePassword("     ")
        //assert
        assertEquals("Password should not be blank", result)
    }

    @Test
    fun validPassword_2CharInput_expectedValidationMsg() {
        //arrange
        val sut = Utils()
        //act
        val result = sut.validatePassword("ab")
        //assert
        assertEquals("Password should be greater than 6", result)
    }

    @Test
    fun validPassword_16CharInput_expectedValidationMsg() {
        //arrange
        val sut = Utils()
        //act
        val result = sut.validatePassword("1234567890123456")
        //assert
        assertEquals("Password should be less than 15", result)
    }

    @Test
    fun validPassword_CorrectInput_expectedValidationMsg() {
        //arrange
        val sut = Utils()
        //act
        val result = sut.validatePassword("1234567890")
        //assert
        assertEquals("Valid Password", result)
    }

    //================================
    @Test
    fun testStringReversal_emptyString_expectedEmptyString() {
        val sut = Utils()
        val result = sut.reverseString("")
        assertEquals("", result)
    }

    @Test
    fun testStringReversal_SingleCharString_expectedSingleCharString() {
        val sut = Utils()
        val result = sut.reverseString("a")
        assertEquals("a", result)
    }

    @Test
    fun testStringReversal_ValidInput_expectedReversedString() {
        val sut = Utils()
        val result = sut.reverseString("kishorramani")
        assertEquals("inamarrohsik", result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testStringReversal_NullInput_expectedException() {
        val sut = Utils()
        val result = sut.reverseString(null)
    }
}