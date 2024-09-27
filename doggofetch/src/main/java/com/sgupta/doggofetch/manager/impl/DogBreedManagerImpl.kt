package com.sgupta.doggofetch.manager.impl

import com.sgupta.doggofetch.data.source.DataSource
import com.sgupta.doggofetch.manager.DogBreedManager

internal class DogBreedManagerImpl(
    private val localDataSource: DataSource,
    private val remoteDataSource: DataSource
) : DogBreedManager {

    private var currentImageIndex = 0

    override suspend fun getImage(): String {
        currentImageIndex = 1
        return localDataSource.getImage(currentImageIndex).ifEmpty {
            remoteDataSource.getImage(currentImageIndex)
        }
    }

    override suspend fun getImages(number: Int): List<String> {
        return localDataSource.getImages(number).ifEmpty {
            remoteDataSource.getImages(number)
        }
    }

    override suspend fun getNextImage(): String {
        currentImageIndex++
        return localDataSource.getImage(currentImageIndex).ifEmpty {
            remoteDataSource.getImage(currentImageIndex)
        }
    }

    override suspend fun getPreviousImage(): String {
        currentImageIndex--
        return localDataSource.getImage(currentImageIndex)
    }

}