package com.sgupta.doggofetch.domain.usecase

import com.sgupta.doggofetch.core.Resource
import com.sgupta.doggofetch.domain.repo.DogBreedRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repo: DogBreedRepo
) {
    operator fun invoke(param: Param) = flow {
        emit(Resource.Loading)
        val images = repo.getImages(param.number)
        emit(Resource.Success(images))
    }

    data class Param(val number: Int)
}