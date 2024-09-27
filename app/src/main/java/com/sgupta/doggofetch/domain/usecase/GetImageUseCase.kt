package com.sgupta.doggofetch.domain.usecase

import com.sgupta.doggofetch.core.Resource
import com.sgupta.doggofetch.domain.repo.DogBreedRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val dogBreedRepo: DogBreedRepo
) {
    operator fun invoke() = flow {
        emit(Resource.Loading)
        val image = dogBreedRepo.getImage()
        emit(Resource.Success(image))
    }
}