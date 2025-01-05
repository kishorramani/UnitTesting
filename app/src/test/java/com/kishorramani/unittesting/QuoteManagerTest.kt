package com.kishorramani.unittesting

import android.content.Context
import android.content.res.AssetManager
import com.nhaarman.mockitokotlin2.doReturn
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

//mockito examples
class QuoteManagerTest {
    @Mock
    lateinit var context: Context

    @Mock
    lateinit var assetManager: AssetManager

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(context.assets).thenReturn(assetManager)
    }

    @Test
    fun test() {
        //Put quotes.json file into the resources folder and read it as a stream
        val testStream = QuoteManagerTest::class.java.getResourceAsStream("/quotes.json")
        //link assetManager and context
        doReturn(assetManager).`when`(context).assets
        Mockito.`when`(context.assets.open(anyString())).thenReturn(testStream)

        val sut = QuoteManager()
        sut.populateQuotesFromAssets(context, "")

        val quote = sut.getCurrentQuote()
        assertEquals("This is quote 1", quote.quote)
    }
}