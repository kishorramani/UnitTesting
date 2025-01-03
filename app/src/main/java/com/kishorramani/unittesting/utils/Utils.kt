package com.kishorramani.unittesting.utils

class Utils {
    //password
    fun validatePassword(input: String) = when {
        input.isBlank() -> "Password should not be blank"

        input.length < 6 -> "Password should be greater than 6"

        input.length > 15 -> "Password should be less than 15"

        else -> "Valid Password"
    }

    //==============================

    fun reverseString(input: String?): String {
        if (input == null) {
            throw IllegalArgumentException("Input String is Required")
        }
        var chars = input.toCharArray()
        var i = 0
        var j = chars.size - 1
        while (i < j) {
            val temp = chars[i]
            chars[i] = chars[j]
            chars[j] = temp
            i++
            j--
        }
        return chars.joinToString("")
    }
}