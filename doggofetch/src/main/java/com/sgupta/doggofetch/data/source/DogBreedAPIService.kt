package com.sgupta.doggofetch.data.source

import com.sgupta.doggofetch.model.BaseDataModelResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DogBreedAPIService {
    @GET("breeds/image/random/")
    suspend fun getImage(): BaseDataModelResponse<String>

    @GET("breeds/image/random/{number}")
    suspend fun getImages(@Path("number") number: Int): BaseDataModelResponse<List<String>>
}