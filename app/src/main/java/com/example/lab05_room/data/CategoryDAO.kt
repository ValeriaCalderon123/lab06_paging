package com.example.lab05_room.data

import androidx.room.*
import com.example.lab05_room.data.entity.Category

@Dao
interface CategoryDAO {
    @Query("SELECT * FROM Category")
    suspend fun getAll(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(animal: Category)

    @Update
    suspend fun update(animal: Category)

    @Delete
    suspend fun delete(animal: Category)
}