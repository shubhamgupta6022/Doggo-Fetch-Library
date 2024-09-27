package com.sgupta.doggofetch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_breed_list")
internal data class DogBreedList(
    @PrimaryKey val id: Int,
    val url: List<String>
)