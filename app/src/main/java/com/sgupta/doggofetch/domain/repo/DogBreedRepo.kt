package com.sgupta.doggofetch.domain.repo

interface DogBreedRepo {
    suspend fun getImage(): String
    suspend fun getImages(number: Int): List<String>
    suspend fun getNextImage(): String
    suspend fun getPreviousImage(): String
}