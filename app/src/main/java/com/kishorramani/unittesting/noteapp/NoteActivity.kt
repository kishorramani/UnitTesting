package com.kishorramani.unittesting.noteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kishorramani.unittesting.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private val binding: ActivityNoteBinding by lazy {
        ActivityNoteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btSubmit.setOnClickListener {
            val msg = "Title - ${binding.etTitle.text} | Description - ${binding.etDescription.text}"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("KEY", msg)
            startActivity(intent)
        }
    }
}