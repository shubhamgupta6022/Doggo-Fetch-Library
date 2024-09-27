package com.sgupta.doggofetch.data.repoimpl

import com.sgupta.doggofetch.DogBreedSDK
import com.sgupta.doggofetch.domain.repo.DogBreedRepo
import javax.inject.Inject

class DogBreedRepoImpl @Inject constructor(
    private val dogBreedSDK: DogBreedSDK
) : DogBreedRepo {

    override suspend fun getImage(): String {
        return dogBreedSDK.getImage()
    }

    override suspend fun getImages(number: Int): List<String> {
        return dogBreedSDK.getImages(number)
    }

    override suspend fun getNextImage(): String {
        return dogBreedSDK.getNextImage()
    }

    override suspend fun getPreviousImage(): String {
        return dogBreedSDK.getPreviousImage()
    }

}