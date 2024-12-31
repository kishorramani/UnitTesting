package com.kishorramani.unittesting.utils

class Helper {
    fun isPallindrome(input: String): Boolean {
        var i = 0
        var j = input.length - 1
        var result = true
        while (i < j) {
            if (input[i] != input[j]) {
                return false
            }
            i++
            j--
        }
        return result
    }
}