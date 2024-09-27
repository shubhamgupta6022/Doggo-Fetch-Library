package com.sgupta.doggofetch.core.image.di

import com.sgupta.doggofetch.core.image.loader.ImageLoader
import com.sgupta.doggofetch.core.image.loader.impl.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageModule {
    @Binds
    abstract fun bindImageLoaderImpl(impl: ImageLoaderImpl): ImageLoader
}