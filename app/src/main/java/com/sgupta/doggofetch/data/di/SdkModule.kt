package com.sgupta.doggofetch.data.di

import android.content.Context
import com.sgupta.doggofetch.DogBreedSDK
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SdkModule {

    @Provides
    @Singleton
    fun providesDogBreedSdkInstance(@ApplicationContext context: Context): DogBreedSDK {
        return DogBreedSDK.getInstance(context)
    }

}