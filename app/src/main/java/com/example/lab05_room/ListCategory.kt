package com.example.lab05_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.room.Room
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Category
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_list_category.*

class ListCategory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_category)
        val roo: SaveAnimalsDB =
            Room.databaseBuilder(this, SaveAnimalsDB::class.java, "animals")
                .fallbackToDestructiveMigration().build()

        listCategory.layoutManager = LinearLayoutManager(this)
        val intentUpdate = Intent(this, UpdateCategory::class.java)
        lifecycleScope.launch {
            val people = roo.categoryDao().getAll();
            val adapter = CategoryAdapter(people)
            Log.d("Person:", people.toString())
            listCategory.adapter = adapter
            adapter.setOnItemClickListener(object : CategoryAdapter.onItemClickListener {
                override fun onItemClick(ca: Category) {
                    //Toast.makeText(this@ListCategory, "You click.$ca", Toast.LENGTH_LONG).show()
                    intentUpdate.apply {
                        putExtra("idCategory", ca.id_category)
                        putExtra("nameCategory", ca.name_category)
                        putExtra("description", ca.description)
                        putExtra("numberCategory", ca.quantity_animals_extinction)
                        putExtra("activeCategory", ca.state_category)
                    }
                    startActivity(intentUpdate)
                }
            })
        }

        var btnSave = findViewById<FloatingActionButton>(R.id.addCategoryButton)

        btnSave.setOnClickListener {
            val intent = Intent(this, CreateCategory::class.java)
            startActivity(intent)
        }
    }

}