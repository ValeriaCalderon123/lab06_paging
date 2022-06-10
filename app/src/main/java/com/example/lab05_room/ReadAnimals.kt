package com.example.lab05_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Animals
import com.example.lab05_room.data.entity.Category
import kotlinx.coroutines.launch

class ReadAnimals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_animals)

        val roo: SaveAnimalsDB =
            Room.databaseBuilder(this, SaveAnimalsDB::class.java, "save2")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build()

        val idAnimal: Int = intent.getIntExtra("id_animal", -1)
        val idAnimalTV: TextView = findViewById(R.id.tvIdAnimalRead)
        val nameAnimal: TextView = findViewById(R.id.tvNameAnimalRead)
        val categoryAnimal: TextView = findViewById(R.id.tvCategoryAnimalRead)
        val statusAnimal: TextView = findViewById(R.id.tvStatusAnimalRead)
        Log.e("ID", "" + idAnimal)

        lifecycleScope.launch {
            val animal: Animals = roo.animalDao().getAnimalById(idAnimal)
            val categoryList: List<Category> = roo.categoryDao().getAll()
            var nameCategory = ""
            for (category in categoryList) {
                if (category.id_category == animal.id_category) {
                    nameCategory = category.name_category
                }
            }
            idAnimalTV.text = animal.id_animal.toString()
            nameAnimal.text = animal.name_animal
            categoryAnimal.text = nameCategory
            if (animal.status_animal) {
                statusAnimal.text = "En peligro"
            } else {
                statusAnimal.text = "Fuera de Peligro"
            }
        }

        val btnEditAnimal: Button = findViewById(R.id.btnEditAnimal)
        val btnDeleteAnimal: Button = findViewById(R.id.btnDeleteAnimal)

        btnEditAnimal.setOnClickListener {
            val intent = Intent(this, UpdateAnimals::class.java)
            intent.putExtra("id_animal", idAnimal)
            startActivity(intent)
        }
        btnDeleteAnimal.setOnClickListener {
            lifecycleScope.launch {
                val animal: Animals = roo.animalDao().getAnimalById(idAnimal)
                roo.animalDao().delete(animal)
            }
            val intent = Intent(this, ListAnimals::class.java)
            startActivity(intent)
        }

    }
}