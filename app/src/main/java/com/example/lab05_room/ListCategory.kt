package com.example.lab05_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.room.Room
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Category

class ListCategory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_category)
        val roo: SaveAnimalsDB =
            Room.databaseBuilder(this, SaveAnimalsDB::class.java, "save2").build()

        lifecycleScope.launch {
            val people = roo.categoryDao().getAll();
            roo.categoryDao().insert(
                Category(0, "AVES", 15, true)
            )
            roo.categoryDao().insert(
                Category(0, "MAMIFEROS", 45, true)
            )
        }

        var btnSave = findViewById<Button>(R.id.button)

        btnSave.setOnClickListener {
            lifecycleScope.launch {
                val people = roo.categoryDao().getAll();
                Log.d("PersonVALEEE:", "MSES")
                for (p in people) {
                    Log.d("Person:", p.name_category)
                }

            }
        }
    }
}