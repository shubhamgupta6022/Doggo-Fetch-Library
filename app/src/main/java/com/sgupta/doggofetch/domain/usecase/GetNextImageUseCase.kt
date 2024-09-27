package com.sgupta.doggofetch.domain.usecase

import com.sgupta.doggofetch.core.Resource
import com.sgupta.doggofetch.domain.repo.DogBreedRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNextImageUseCase @Inject constructor(private val repo: DogBreedRepo) {
    operator fun invoke() = flow {
        emit(Resource.Loading)
        val nextImage = repo.getNextImage()
        emit(Resource.Success(nextImage))
    }
}