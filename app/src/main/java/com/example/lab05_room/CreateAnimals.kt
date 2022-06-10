package com.example.lab05_room

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Animals
import com.example.lab05_room.data.entity.Category
import kotlinx.coroutines.launch


class CreateAnimals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_animals)

        //DataBase
        val roo: SaveAnimalsDB =
            Room.databaseBuilder(this, SaveAnimalsDB::class.java, "save2")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build()

        val edtNameAnimal: EditText = findViewById(R.id.etNameAnimal)
        val spnCategoryList: Spinner = findViewById(R.id.spnCategoryList)
        val rgStatusAnimal: RadioGroup = findViewById(R.id.rbStatusAnimal)

        //Setting the spinner
        var categoryList: List<Category> = ArrayList()
        var arrayNameCategory: List<String> = ArrayList<String>()
        lifecycleScope.launch {
            categoryList = roo.categoryDao().getAll()
            arrayNameCategory = categoryList.map { it.name_category }
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                applicationContext, android.R.layout.simple_spinner_item, arrayNameCategory
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnCategoryList.adapter = adapter
        }

        val btnSaveAnimal: Button = findViewById(R.id.btnSaveAnimal)
        val btnCancelAnimal: Button = findViewById(R.id.btnCancelAnimal)

        btnSaveAnimal.setOnClickListener {
            val nameAnimal = edtNameAnimal.text.toString()
            val categorySelected = spnCategoryList.selectedItem.toString()
            var idCategory: Int = -1
            val radioChecked: Int = rgStatusAnimal.checkedRadioButtonId
            var status = true
            for (category in categoryList) {
                if (category.name_category == categorySelected) {
                    idCategory = category.id_category
                }
            }
            if (radioChecked != -1 && idCategory != -1) {
                val radio: RadioButton = findViewById(radioChecked)
                if (radio.text.toString() != "En Peligro") {
                    status = false
                }
                lifecycleScope.launch {
                    roo.animalDao().insert(
                        Animals(
                            0,
                            nameAnimal,
                            idCategory,
                            status
                        )
                    )
                }
                val intent = Intent(this, ListAnimals::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Debe seleccionar el estado del animal",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnCancelAnimal.setOnClickListener {
            val intent = Intent(this, ListAnimals::class.java)
            startActivity(intent)
        }

    }
}