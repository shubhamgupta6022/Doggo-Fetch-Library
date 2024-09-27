package com.sgupta.doggofetch.data.source.impl

import com.sgupta.doggofetch.data.dao.DogBreedDao
import com.sgupta.doggofetch.data.dao.DogBreedListDao
import com.sgupta.doggofetch.data.source.DataSource

internal class LocalDataSource(
    private val dogBreedDao: DogBreedDao?,
    private val dogBreedListDao: DogBreedListDao?
) : DataSource {

    override suspend fun getImage(index: Int): String {
        val item = dogBreedDao?.getImageById(index)
        return item?.url ?: ""
    }

    override suspend fun getImages(number: Int): List<String> {
        return dogBreedListDao?.getImageListById(number)?.url.orEmpty()
    }

}