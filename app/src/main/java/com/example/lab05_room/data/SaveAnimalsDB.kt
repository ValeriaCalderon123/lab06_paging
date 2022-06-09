package com.example.lab05_room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab05_room.data.entity.Category
import com.example.lab05_room.data.entity.Animals

@Database(entities = [Animals::class], version = 1)
abstract class SaveAnimalsDB: RoomDatabase() {
    abstract fun animalDao(): AnimalsDAO
}