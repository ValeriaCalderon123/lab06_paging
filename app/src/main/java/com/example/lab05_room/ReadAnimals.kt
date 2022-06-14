package com.example.lab05_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ReadAnimals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_animals)

        val idAnimal: Int = intent.getIntExtra("id_animal", -1)
        val idAnimalTV: TextView = findViewById(R.id.tvIdAnimalRead)
        val nameAnimal: TextView = findViewById(R.id.tvNameAnimalRead)
        val categoryAnimal: TextView = findViewById(R.id.tvCategoryAnimalRead)
        val statusAnimal: TextView = findViewById(R.id.tvStatusAnimalRead)
        Log.e("ID", "" + idAnimal)


    }
}