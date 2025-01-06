package com.kishorramani.unittesting.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineTesting(val dispatcher: CoroutineDispatcher? = null) {

    suspend fun getUserName(): String {
        delay(10000)
        return "KishorRamani"
    }

    suspend fun getUser(): String {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
        }
        return "User - KishorRamani"
    }

    suspend fun getAddress(): String {
        dispatcher?.let {
            withContext(it) {
                delay(5000)
            }
        }
        return "Surat"
    }

    var globalArg = false
    fun getAddressDetails() {
        if (dispatcher != null) {
            CoroutineScope(dispatcher).launch {
                globalArg = true
            }
        }
    }
}