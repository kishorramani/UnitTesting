package com.kishorramani.unittesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.kishorramani.unittesting.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuoteDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    //A JUnit Test Rule that swaps the background executor used by the Architecture Components
    // with a different one which executes each task synchronously.

    lateinit var quoteDatabase: QuoteDatabase
    lateinit var quoteDao: QuoteDao

    @Before
    fun setup() {
        //create room database setup in memory(when app close, database is close)
        //On every test it's create new database,
        //Our query is run on main thread - we don't want to run it on another thread, we want that it's run on only one thread
        quoteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()
        quoteDao = quoteDatabase.quoteDao()
    }

    //runBlocking block the thread until every coroutine is complete
    @Test
    fun insertQuote_expectedSingleQuote() = runBlocking {
        val quote = QuoteEntity(0, "This is a test quote", "Test")
        quoteDao.insertQuote(quote)

        //quoteDao.getQuotes() -> it's live data, block this until the data is comes
        val result = quoteDao.getQuotes().getOrAwaitValue()
        Assert.assertEquals(1, result.size)
        Assert.assertEquals("This is a test quote", result[0].text)
    }

    @Test
    fun deleteQuote_expectedNoResult() = runBlocking {
        val quote = QuoteEntity(0, "This is a test quote", "Test")
        quoteDao.insertQuote(quote)

        quoteDao.deleteQuote()

        //quoteDao.getQuotes() -> it's live data, block this until the data is comes
        val result = quoteDao.getQuotes().getOrAwaitValue()
        Assert.assertEquals(0, result.size)
    }

    @After
    fun tearDown() {
        quoteDatabase.close()
    }
}