package com.kishorramani.unittesting.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quoteEntity: QuoteEntity)

    @Update
    suspend fun updateQuote(quoteEntity: QuoteEntity)

    @Query("DELETE from QuoteEntity")
    suspend fun deleteQuote()

    @Query("SELECT * from QuoteEntity")
    fun getQuotes(): LiveData<List<QuoteEntity>>

    @Query("SELECT * from QuoteEntity where id = :quoteId")
    suspend fun getQuoteById(quoteId: Int): QuoteEntity
}

