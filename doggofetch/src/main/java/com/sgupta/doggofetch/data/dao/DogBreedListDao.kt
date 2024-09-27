package com.sgupta.doggofetch.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sgupta.doggofetch.data.entities.DogBreedList

@Dao
internal interface DogBreedListDao: BaseDao<DogBreedList> {

    @Query("SELECT * FROM dog_breed_list WHERE id = :index")
    suspend fun getImageListById(index: Int): DogBreedList?

}