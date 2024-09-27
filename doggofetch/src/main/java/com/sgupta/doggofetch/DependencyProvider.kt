package com.sgupta.doggofetch

import android.content.Context
import com.sgupta.doggofetch.constants.BASE_URL
import com.sgupta.doggofetch.data.source.DogBreedAPIService
import com.sgupta.doggofetch.data.dao.DogBreedDao
import com.sgupta.doggofetch.data.dao.DogBreedListDao
import com.sgupta.doggofetch.data.db.DoggoDatabase
import com.sgupta.doggofetch.data.db.DoggoDatabaseProvider
import com.sgupta.doggofetch.data.source.DataSourceFactory
import com.sgupta.doggofetch.manager.DogBreedManager
import com.sgupta.doggofetch.manager.impl.DogBreedManagerImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object DependencyProvider {

    fun providesDogManager(context: Context): DogBreedManager {
        val localDataSource =
            DataSourceFactory.create(DataSourceFactory.DataSourceState.LOCAL, context)
        val remoteDataSource =
            DataSourceFactory.create(DataSourceFactory.DataSourceState.REMOTE, context)
        return DogBreedManagerImpl(localDataSource, remoteDataSource)
    }

    private fun providesDoggoDatabase(context: Context): DoggoDatabaseProvider {
        return DoggoDatabase.getDatabase(context)
    }

    fun providesDogBreedDao(context: Context): DogBreedDao? {
        val database = providesDoggoDatabase(context) as? DoggoDatabase
        return database?.dogBreedDao()
    }

    fun providesDogBreedListDao(context: Context): DogBreedListDao? {
        val database = providesDoggoDatabase(context) as? DoggoDatabase
        return database?.dogBreedListDao()
    }

    fun providesDogCeoAPIService(): DogBreedAPIService {
        return providesRetrofit().create(DogBreedAPIService::class.java)
    }

    private fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

