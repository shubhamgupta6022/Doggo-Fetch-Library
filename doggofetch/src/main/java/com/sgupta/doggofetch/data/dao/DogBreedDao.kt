package com.sgupta.doggofetch.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sgupta.doggofetch.data.entities.DogBreedImage

@Dao
internal interface DogBreedDao: BaseDao<DogBreedImage> {

    @Query("SELECT * FROM dog_breed_images WHERE id = :index")
    suspend fun getImageById(index: Int): DogBreedImage?
}