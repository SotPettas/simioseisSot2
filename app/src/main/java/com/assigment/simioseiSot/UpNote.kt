package com.assigment.simioseiSot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.widget.Button
import com.assigment.simioseiSot.ViewModel.infoViewF

import com.assigment.simioseiSot.databinding.UpNoteBinding

class UpNote : AppCompatActivity() {





            private val binding: UpNoteBinding by lazy {
        UpNoteBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContentView(binding.root)

        binding.createUpNoteButton.setOnClickListener {

            startActivity(Intent(this, AddUpNote::class.java))


        }

        binding.openNoteUpButton.setOnClickListener {

            startActivity(Intent(this, AllUpNotes::class.java))


        }
        val buttonClick = findViewById<Button>(R.id.button5)
        buttonClick.setOnClickListener {
            val intent = Intent(this, infoViewF::class.java)
            startActivity(intent)

    }

}}