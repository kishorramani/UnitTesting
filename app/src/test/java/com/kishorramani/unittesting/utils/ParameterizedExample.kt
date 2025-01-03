package com.kishorramani.unittesting.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ParameterizedExample(private val input: String, private val expectedValue: Boolean) {
    @Test
    fun test() {
        val helper = Helper()
        val result = helper.isPallindrome(input)
        assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is pallindrome - {1}")      //0 & 1 are index of items, to print index - write {index}
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf("hello", false),
                arrayOf("level", true),
                arrayOf("", true),
                arrayOf("a", true)
            )
        }
    }
}