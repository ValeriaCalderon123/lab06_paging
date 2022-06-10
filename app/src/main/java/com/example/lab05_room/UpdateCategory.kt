package com.example.lab05_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Category
import kotlinx.android.synthetic.main.activity_update_category.*
import kotlinx.coroutines.launch

class UpdateCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_update_category)

        val roo: SaveAnimalsDB =
            Room.databaseBuilder(this, SaveAnimalsDB::class.java, "animals").fallbackToDestructiveMigration().build()

        val category_name: String? = intent.getStringExtra("nameCategory")
        val category_descripcion: String? = intent.getStringExtra("description")
        val category_number: Int = intent.getIntExtra("numberCategory",1)
        val category_status: Boolean= intent.getBooleanExtra("description",true)

        editNameCategoryV.setText(category_name)
        editdescripcioncategoryV.setText(category_descripcion)
        editnumberCategoryV.setText(category_number.toString())
        editStateCategoryV.setText(category_status.toString())

        saveButtonCategoryV.setOnClickListener {
            lifecycleScope.launch {
                val updateC = roo.categoryDao().update(
                    Category(editNameCategoryV.text.toString() , editdescripcioncategoryV.text.toString(),Integer.parseInt(editnumberCategoryV.text.toString()), java.lang.Boolean.valueOf(editStateCategoryV.text.toString()))
                );
            }
            val intent = Intent(this, ListCategory::class.java)
            startActivity(intent)
        }
        deleteCategory.setOnClickListener {
            lifecycleScope.launch {
                val deleteC = roo.categoryDao().delete(
                    Category(editNameCategoryV.text.toString() , editdescripcioncategoryV.text.toString(),Integer.parseInt(editnumberCategoryV.text.toString()),
                        java.lang.Boolean.valueOf(editStateCategoryV.text.toString())
                    )
                );
            }
            val intent = Intent(this, ListCategory::class.java)
            startActivity(intent)
        }
        back2Button.setOnClickListener {
            val intent = Intent(this, ListCategory::class.java)
            startActivity(intent)
        }

    }
}