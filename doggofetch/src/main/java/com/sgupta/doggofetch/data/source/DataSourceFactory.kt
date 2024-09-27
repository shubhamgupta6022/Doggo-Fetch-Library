package com.sgupta.doggofetch.data.source

import android.content.Context
import com.sgupta.doggofetch.DependencyProvider
import com.sgupta.doggofetch.data.source.impl.LocalDataSource
import com.sgupta.doggofetch.data.source.impl.RemoteDataSource

internal object DataSourceFactory {

    enum class DataSourceState(val id: String) {
        LOCAL("local"),
        REMOTE("remote")
    }

    fun create(state: DataSourceState, context: Context): DataSource {
        return when (state) {
            DataSourceState.LOCAL -> {
                val dogBreedDao = DependencyProvider.providesDogBreedDao(context)
                val dogBreedListDao = DependencyProvider.providesDogBreedListDao(context)
                LocalDataSource(dogBreedDao, dogBreedListDao)
            }

            DataSourceState.REMOTE -> {
                val dogBreedDao = DependencyProvider.providesDogBreedDao(context)
                val dogBreedListDao = DependencyProvider.providesDogBreedListDao(context)
                val dogBreedApiService = DependencyProvider.providesDogCeoAPIService()
                RemoteDataSource(dogBreedApiService, dogBreedDao, dogBreedListDao)
            }
        }
    }
}