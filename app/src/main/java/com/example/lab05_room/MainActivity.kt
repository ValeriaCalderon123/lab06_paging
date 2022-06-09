package com.example.lab05_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCRUDCategory: Button = findViewById(R.id.btnCRUDCategory)
        val btnCRUDAnimals: Button = findViewById(R.id.btnCRUDAnimals)

        btnCRUDCategory.setOnClickListener {
            val intent = Intent(this, ListCategory::class.java)
            startActivity(intent)
        }

        btnCRUDAnimals.setOnClickListener {
            val intent = Intent(this, ListAnimals::class.java)
            startActivity(intent)
        }
    }
}