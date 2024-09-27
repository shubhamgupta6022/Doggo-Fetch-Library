package com.sgupta.doggofetch.data.di

import com.sgupta.doggofetch.data.repoimpl.DogBreedRepoImpl
import com.sgupta.doggofetch.domain.repo.DogBreedRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    abstract fun bindDogBreedRepo(impl: DogBreedRepoImpl): DogBreedRepo
}