package com.example.lab05_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.lab05_room.data.MAnimal
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Animals
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ListAnimals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_animals)

        val roo: SaveAnimalsDB =
            Room.databaseBuilder(this, SaveAnimalsDB::class.java, "animals")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build()

        lifecycleScope.launch {
            val rvListAnimals: RecyclerView = findViewById(R.id.rvListAnimals)

            rvListAnimals.apply {
                layoutManager = LinearLayoutManager(this@ListAnimals)
                val allAnimals: List<Animals> = roo.animalDao().getAll()
                val modifiedAnimals: ArrayList<MAnimal> = ArrayList()
                for (animal in allAnimals) {
                    modifiedAnimals.add(
                        MAnimal(
                            animal.id_animal,
                            animal.name_animal,
                            "Nuevo",
                            animal.status_animal
                        )
                    )
                }
                adapter = AnimalsAdapter(modifiedAnimals).apply {
                    this.setOnItemClickListener(object : AnimalsAdapter.onItemClickListener {
                        override fun onItemClick(animal: MAnimal) {
                            val intent = Intent(this@ListAnimals, ReadAnimals::class.java).apply {
                                putExtra("id_animal", animal.id_animal)
                            }
                            startActivity(intent)
                        }
                    })
                }
            }
        }

    }
}