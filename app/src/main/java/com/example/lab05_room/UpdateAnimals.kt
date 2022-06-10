package com.example.lab05_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.lab05_room.data.SaveAnimalsDB
import com.example.lab05_room.data.entity.Animals
import com.example.lab05_room.data.entity.Category
import kotlinx.coroutines.launch

class UpdateAnimals : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_animals)

        val roo: SaveAnimalsDB =
            Room.databaseBuilder(this, SaveAnimalsDB::class.java, "save2")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build()

        val idAnimal: Int = intent.getIntExtra("id_animal", -1)

        val edtNameAnimal: EditText = findViewById(R.id.etUpdateNameAnimal)
        val spnCategoryList: Spinner = findViewById(R.id.spnUpdateCategoryList)
        val rgStatusAnimal: RadioGroup = findViewById(R.id.rbUpdateStatusAnimal)

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

            val animal: Animals = roo.animalDao().getAnimalById(idAnimal)
            edtNameAnimal.setText(animal.name_animal)
            var idx = -1
            for (cat in categoryList) {
                if (cat.id_category == animal.id_category)
                    idx = animal.id_category
            }
            spnCategoryList.setSelection(idx - 1)
            if (animal.status_animal) {
                rgStatusAnimal.check(R.id.rbUpdateDanger)
            } else {
                rgStatusAnimal.check(R.id.rbUpdateSafe)
            }
        }

        val btnUpdateAnimal: Button = findViewById(R.id.btnUpdateAnimal)
        val btnCancelUpdateAnimal: Button = findViewById(R.id.btnCancelUpdateAnimal)

        btnUpdateAnimal.setOnClickListener {
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
                    roo.animalDao().update(
                        Animals(
                            idAnimal,
                            nameAnimal,
                            idCategory,
                            status
                        )
                    )
                }
                val intent = Intent(this, ReadAnimals::class.java)
                intent.putExtra("id_animal", idAnimal)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Debe seleccionar el estado del animal",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnCancelUpdateAnimal.setOnClickListener {
            val intent = Intent(this, ReadAnimals::class.java)
            intent.putExtra("id_animal", idAnimal)
            startActivity(intent)
        }

    }
}