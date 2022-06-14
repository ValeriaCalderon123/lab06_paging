package com.example.lab05_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnListAnimals: Button = findViewById(R.id.btnListAnimals)

        btnListAnimals.setOnClickListener {
            val intent = Intent(this, ListAnimals::class.java)
            startActivity(intent)
        }
    }
}