package com.example.lab05_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Category
import kotlinx.coroutines.launch

class ListAnimals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_animals)

        val roo: SaveAnimalsDB = Room.databaseBuilder(this, SaveAnimalsDB::class.java, "save2").build()

        lifecycleScope.launch { val people = roo.categoryDao().getAll()}

    }
}