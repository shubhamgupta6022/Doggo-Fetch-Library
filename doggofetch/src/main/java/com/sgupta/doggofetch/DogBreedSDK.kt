package com.sgupta.doggofetch

import android.content.Context

class DogBreedSDK(private val applicationContext: Context) {

    companion object {
        @Volatile
        private var sInstance: DogBreedSDK? = null

        fun getInstance(context: Context): DogBreedSDK {
            return sInstance ?: synchronized(this) {
                sInstance ?: DogBreedSDK(context.applicationContext).also {
                    sInstance = it
                }
            }
        }
    }

    private val dogBreedManager by lazy {
        DependencyProvider.providesDogManager(applicationContext)
    }

    suspend fun getImage(): String {
        return dogBreedManager.getImage()
    }

    suspend fun getImages(number: Int): List<String> {
        return dogBreedManager.getImages(number)
    }

    suspend fun getNextImage(): String {
        return dogBreedManager.getNextImage()
    }

    suspend fun getPreviousImage(): String {
        return dogBreedManager.getPreviousImage()
    }

}