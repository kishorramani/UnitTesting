package com.kishorramani.unittesting.quoteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kishorramani.unittesting.databinding.ActivityQuoteBinding

class QuoteActivity : AppCompatActivity() {
    private val binding: ActivityQuoteBinding by lazy {
        ActivityQuoteBinding.inflate(layoutInflater)
    }

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
    }

    fun setQuote(quote: Quote) {
        binding.quoteText.text = quote.quote
        binding.quoteAuthor.text = quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().quote)
        startActivity(intent)
    }
}