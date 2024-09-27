package com.sgupta.doggofetch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_breed_images")
internal data class DogBreedImage(
    @PrimaryKey val id: Int,
    val url: String
)