package com.example.lab05_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Category
import kotlinx.android.synthetic.main.activity_create_category.*
import kotlinx.coroutines.launch
import java.lang.Boolean.valueOf

class CreateCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        val roo: SaveAnimalsDB =
            Room.databaseBuilder(this, SaveAnimalsDB::class.java, "animals").
            fallbackToDestructiveMigration().build()
        var btnSave = findViewById<Button>(R.id.saveButtonCategory)
        var btnBack = findViewById<Button>(R.id.backCategory)

        btnSave.setOnClickListener {
            lifecycleScope.launch {
                    val categoryinsert = roo.categoryDao().insert(
                    Category(editNameCategory.text.toString() , editdescripcioncategory.text.toString(),
                        Integer.parseInt(editnumberCategory.text.toString()), valueOf(editStateCategory.text.toString()))
                );
            }
            val intent = Intent(this, ListCategory::class.java)
            startActivity(intent)
        }
        btnBack.setOnClickListener {
            val intent = Intent(this, ListCategory::class.java)
            startActivity(intent)
        }



    }
}