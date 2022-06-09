package com.example.lab05_room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Animals(
    @PrimaryKey(autoGenerate = true) val id_animal: Int,
    val name_animal: String,
    val id_category: Int,
    val status_animal: Boolean
)