package com.kishorramani.unittesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import org.junit.Assert.*

import org.junit.Test
import java.io.FileNotFoundException

class QuoteManagerTest {

    @Test(expected = FileNotFoundException::class)      //Asserts - We know that it should give file not found exception
    fun populateQuotesFromAssets() {
        //Arrange - Get all details
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Act - get quotes from assets
        quoteManager.populateQuotesFromAssets(context, "")
    }

    @Test(expected = JsonSyntaxException::class)      //Asserts - We know that it should give file not found exception
    fun testPopulateQuotesFromAssets_InvalidJSON_expected_Exception() {
        //Arrange - Get all details
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Act - get quotes from assets
        quoteManager.populateQuotesFromAssets(context, "malformed.json")
    }

    @Test
    fun testPopulateQuotesFromAssets_ValidJSON_expected_Count() {
        //Arrange - Get all details
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Act - get quotes from assets
        quoteManager.populateQuotesFromAssets(context, "quotes.json")
        //Assert - check count
        assertEquals(6, quoteManager.quotesList.size)
    }

    @Test
    fun testPreviousQuote_expected_CorrectQuote() {
        //Arrange - Get all details
        val quoteManager = QuoteManager()
        quoteManager.populateQuotes(
            arrayOf(
                Quote("This is quote 1", "Author 1"),
                Quote("This is quote 2", "Author 2"),
                Quote("This is quote 3", "Author 3"),
            )
        )
        //Act - get quotes from assets
        val quote = quoteManager.getPreviousQuote()
        //Assert - check Author of quote 1
        assertEquals("Author 1", quote.author)
    }

    @Test
    fun testNextQuote_expected_CorrectQuote() {
        //Arrange - Get all details
        val quoteManager = QuoteManager()
        quoteManager.populateQuotes(
            arrayOf(
                Quote("This is quote 1", "Author 1"),
                Quote("This is quote 2", "Author 2"),
                Quote("This is quote 3", "Author 3"),
            )
        )
        //Act - get quotes from assets
        val quote = quoteManager.getNextQuote()
        //Assert - check Author of quote 1
        assertEquals("Author 2", quote.author)
    }
}