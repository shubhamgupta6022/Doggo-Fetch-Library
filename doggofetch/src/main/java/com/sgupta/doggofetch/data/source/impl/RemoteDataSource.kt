package com.sgupta.doggofetch.data.source.impl

import com.sgupta.doggofetch.data.dao.DogBreedDao
import com.sgupta.doggofetch.data.dao.DogBreedListDao
import com.sgupta.doggofetch.data.entities.DogBreedImage
import com.sgupta.doggofetch.data.entities.DogBreedList
import com.sgupta.doggofetch.data.source.DataSource
import com.sgupta.doggofetch.data.source.DogBreedAPIService

internal class RemoteDataSource(
    private val apiService: DogBreedAPIService,
    private val dogBreedDao: DogBreedDao?,
    private val dogBreedListDao: DogBreedListDao?
) : DataSource {

    override suspend fun getImage(index: Int): String {
        val response = apiService.getImage()
        dogBreedDao?.insertOrReplaceItem(DogBreedImage(id = index, url = response.data.orEmpty()))
        return response.data.orEmpty()
    }

    override suspend fun getImages(number: Int): List<String> {
        val list = apiService.getImages(number).data
        dogBreedListDao?.insertOrReplaceItem(DogBreedList(id = number, url = list.orEmpty()))
        return list.orEmpty()
    }
}