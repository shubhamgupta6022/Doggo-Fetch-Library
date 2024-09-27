package com.sgupta.doggofetch.data.source

import com.sgupta.doggofetch.data.dao.DogBreedDao
import com.sgupta.doggofetch.data.dao.DogBreedListDao
import com.sgupta.doggofetch.data.entities.DogBreedImage
import com.sgupta.doggofetch.data.entities.DogBreedList
import com.sgupta.doggofetch.data.source.impl.LocalDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LocalDataSourceTest {

    private lateinit var dogBreedDao: DogBreedDao
    private lateinit var dogBreedListDao: DogBreedListDao
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        dogBreedDao = mock()
        dogBreedListDao = mock()
        localDataSource = LocalDataSource(dogBreedDao, dogBreedListDao)
    }

    @Test
    fun `getImage should return image url from dao when available`() = runTest {
        val imageUrl = "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
        val dogBreedEntity = DogBreedImage(1, imageUrl)

        whenever(dogBreedDao.getImageById(1)).thenReturn(dogBreedEntity)

        val result = localDataSource.getImage(1)

        assertEquals(imageUrl, result)
    }

    @Test
    fun `getImage should return empty string when dao returns null`() = runTest {
        whenever(dogBreedDao.getImageById(1)).thenReturn(null)

        val result = localDataSource.getImage(1)

        assertEquals("", result)
    }

    @Test
    fun `getImages should return image list from dao when available`() = runTest {
        val imageList = listOf(
            "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg",
            "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
        )
        val dogBreedListEntity = DogBreedList(2, imageList)

        whenever(dogBreedListDao.getImageListById(2)).thenReturn(dogBreedListEntity)

        val result = localDataSource.getImages(2)

        assertEquals(imageList, result)
    }

    @Test
    fun `getImages should return empty list when dao returns null`() = runTest {
        whenever(dogBreedListDao.getImageListById(2)).thenReturn(null)

        val result = localDataSource.getImages(2)

        assertEquals(emptyList<String>(), result)
    }
}