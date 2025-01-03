package com.kishorramani.unittesting

import android.content.Context
import com.google.gson.Gson

class QuoteManager {
    var quotesList = emptyArray<Quote>()
    var currentQuoteIndex = 0

    fun populateQuotesFromAssets(context: Context, filename: String) {
        val inputStream = context.assets.open(filename)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        quotesList = gson.fromJson(json, Array<Quote>::class.java)
    }

    fun populateQuotes(quotes: Array<Quote>) {
        quotesList = quotes
    }

    fun getCurrentQuote(): Quote {
        return quotesList[currentQuoteIndex]
    }

    fun getNextQuote(): Quote {
        if (currentQuoteIndex == quotesList.size - 1) return quotesList[currentQuoteIndex]
        return quotesList[++currentQuoteIndex]
    }

    fun getPreviousQuote(): Quote {
        if (currentQuoteIndex == 0) return quotesList[currentQuoteIndex]
        return quotesList[--currentQuoteIndex]
    }

}