package com.kishorramani.unittesting.mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kishorramani.unittesting.R
import com.kishorramani.unittesting.databinding.ActivityProductBinding
import com.kishorramani.unittesting.mvvm.adapters.ProductAdapter
import com.kishorramani.unittesting.mvvm.utils.NetworkResult
import com.kishorramani.unittesting.mvvm.viewmodels.ProductViewModel
import com.kishorramani.unittesting.mvvm.viewmodels.ProductViewModelFactory

class ProductActivity : AppCompatActivity() {

    private val binding: ActivityProductBinding by lazy {
        ActivityProductBinding.inflate(layoutInflater)
    }

    lateinit var productViewModel: ProductViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.productList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val repository = (application as StoreApplication).productRepository
        productViewModel = ViewModelProvider(this, ProductViewModelFactory(repository))[ProductViewModel::class.java]

        productViewModel.getProducts()

        productViewModel.products.observe(this, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d("CHEEZ", it.data.toString())
                    adapter = ProductAdapter(it.data!!)
                    recyclerView.adapter = adapter
                }

                is NetworkResult.Error -> {}

                is NetworkResult.Loading -> {}
            }
        })
    }
}