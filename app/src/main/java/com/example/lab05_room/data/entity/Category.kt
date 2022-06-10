package com.example.lab05_room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category (
    @PrimaryKey// (autoGenerate = true) val id_category: Int,
    val name_category: String,
    val description:String,
    val quantity_animals_extinction: Int,

    val state_category: Boolean
) {

}