package com.sgupta.doggofetch.manager

internal interface DogBreedManager {
    suspend fun getImage(): String
    suspend fun getImages(number: Int): List<String>
    suspend fun getNextImage(): String
    suspend fun getPreviousImage(): String
}