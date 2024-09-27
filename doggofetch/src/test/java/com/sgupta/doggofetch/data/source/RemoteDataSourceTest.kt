package com.sgupta.doggofetch.data.source

import com.sgupta.doggofetch.data.dao.DogBreedDao
import com.sgupta.doggofetch.data.dao.DogBreedListDao
import com.sgupta.doggofetch.data.entities.DogBreedImage
import com.sgupta.doggofetch.data.entities.DogBreedList
import com.sgupta.doggofetch.data.source.impl.RemoteDataSource
import com.sgupta.doggofetch.model.BaseDataModelResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RemoteDataSourceTest {

    private lateinit var apiService: DogBreedAPIService
    private lateinit var dogBreedDao: DogBreedDao
    private lateinit var dogBreedListDao: DogBreedListDao
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        apiService = mock()
        dogBreedDao = mock()
        dogBreedListDao = mock()
        remoteDataSource = RemoteDataSource(apiService, dogBreedDao, dogBreedListDao)
    }

    @Test
    fun `getImage should return image from API and store in dao`() = runTest {
        val apiResponse = BaseDataModelResponse<String>().apply {
            data = "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
        }
        whenever(apiService.getImage()).thenReturn(apiResponse)
        val result = remoteDataSource.getImage(1)
        verify(dogBreedDao).insertOrReplaceItem(
            DogBreedImage(
                id = 1,
                url = "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
            )
        )
        assertEquals("https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg", result)
    }

    @Test
    fun `getImage should return empty string when API returns null and still store in dao`() =
        runTest {
            val apiResponse = BaseDataModelResponse<String>().apply { data = "" }
            whenever(apiService.getImage()).thenReturn(apiResponse)
            val result = remoteDataSource.getImage(1)
            verify(dogBreedDao).insertOrReplaceItem(DogBreedImage(id = 1, url = ""))
            assertEquals("", result)
        }

    @Test
    fun `getImages should return list from API and store in list dao`() = runTest {
        val apiResponse = BaseDataModelResponse<List<String>>().apply {
            data = listOf(
                "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg",
                "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
            )
        }
        whenever(apiService.getImages(2)).thenReturn(apiResponse)
        val result = remoteDataSource.getImages(2)
        verify(dogBreedListDao).insertOrReplaceItem(
            DogBreedList(
                id = 2,
                url = listOf(
                    "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg",
                    "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
                )
            )
        )
        assertEquals(
            listOf(
                "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg",
                "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
            ), result
        )
    }

    @Test
    fun `getImages should return empty list when API returns null and still store in list dao`() =
        runTest {
            val apiResponse = BaseDataModelResponse<List<String>>()
            whenever(apiService.getImages(2)).thenReturn(apiResponse)
            val result = remoteDataSource.getImages(2)
            verify(dogBreedListDao).insertOrReplaceItem(DogBreedList(id = 2, url = emptyList()))
            assertEquals(emptyList<String>(), result)
        }

}