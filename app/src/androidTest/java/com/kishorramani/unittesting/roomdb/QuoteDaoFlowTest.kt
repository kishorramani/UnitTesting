package com.kishorramani.unittesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.kishorramani.unittesting.getOrAwaitValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuoteDaoFlowTest {

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

        //quoteDao.getQuotesFlow() -> it's flow
        //.first() -> It's give first quote, you can use getList() too to get the total size of the quotes
        val result = quoteDao.getQuotesFlow().first()
        Assert.assertEquals(1, result.size)
        Assert.assertEquals("This is a test quote", result[0].text)
    }

    @Test
    fun insertQuote_expectedListQuote() = runBlocking {
        val quote = QuoteEntity(0, "This is a test quote", "Test")
        val quote1 = QuoteEntity(0, "This is a test quote 1", "Test 1")
        quoteDao.insertQuote(quote)
        quoteDao.insertQuote(quote1)

        launch {
            delay(500)
            quoteDao.insertQuote(QuoteEntity(0, "This is a test quote 2", "Test 2"))
        }

        //quoteDao.getQuotesFlow() -> it's flow
        //.toList() -> We observe as a list, so this flow never end, it's infinite flow
        val result = quoteDao.getQuotesFlow().test {
            val quoteList = awaitItem()     //whenever list changes anytime, it's store that into the quoteList
            Assert.assertEquals(2, quoteList.size)
            val quoteList1 = awaitItem()     //whenever list changes anytime, it's store that into the quoteList
            Assert.assertEquals(3, quoteList1.size)
            cancel()        //flow is cancel
        }
    }

    @After
    fun tearDown() {
        quoteDatabase.close()
    }
}