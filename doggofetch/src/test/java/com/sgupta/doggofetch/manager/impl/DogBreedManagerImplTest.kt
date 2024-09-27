package com.sgupta.doggofetch.manager.impl

import com.sgupta.doggofetch.data.source.DataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DogBreedManagerImplTest {

    private lateinit var localDataSource: DataSource
    private lateinit var remoteDataSource: DataSource
    private lateinit var dogBreedManager: DogBreedManagerImpl

    @Before
    fun setUp() {
        localDataSource = mock()
        remoteDataSource = mock()
        dogBreedManager = DogBreedManagerImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `getImage should return image from local if available`() = runTest {
        whenever(localDataSource.getImage(1)).thenReturn(
            "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
        )
        val result = dogBreedManager.getImage()
        assertEquals("https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg", result)
        verify(remoteDataSource, never()).getImage(1)
    }

    @Test
    fun `getImage should return image from remote if local is empty`() = runTest {
        whenever(localDataSource.getImage(1)).thenReturn("")
        whenever(remoteDataSource.getImage(1)).thenReturn(
            "https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg"
        )
        val result = dogBreedManager.getImage()
        assertEquals("https://images.dog.ceo/breeds/pitbull/dog-3981033_1280.jpg", result)
        verify(localDataSource).getImage(1)
        verify(remoteDataSource).getImage(1)
    }

    @Test
    fun `getImages should return images from local if available`() = runTest {
        whenever(localDataSource.getImages(3)).thenReturn(listOf("image1", "image2", "image3"))
        val result = dogBreedManager.getImages(3)
        assertEquals(listOf("image1", "image2", "image3"), result)
        verify(remoteDataSource, never()).getImages(3)
    }

    @Test
    fun `getImages should return images from remote if local is empty`() = runTest {
        whenever(localDataSource.getImages(3)).thenReturn(emptyList())
        whenever(remoteDataSource.getImages(3)).thenReturn(
            listOf(
                "remoteImage1",
                "remoteImage2",
                "remoteImage3"
            )
        )
        val result = dogBreedManager.getImages(3)
        assertEquals(listOf("remoteImage1", "remoteImage2", "remoteImage3"), result)
        verify(localDataSource).getImages(3)
        verify(remoteDataSource).getImages(3)
    }

    @Test
    fun `getNextImage should increment index and return image from local`() = runTest {
        whenever(localDataSource.getImage(1)).thenReturn("image1")
        whenever(localDataSource.getImage(2)).thenReturn("image2")
        dogBreedManager.getImage()
        val result = dogBreedManager.getNextImage()
        assertEquals("image2", result)
        verify(localDataSource).getImage(1)
        verify(localDataSource).getImage(2)
    }

    @Test
    fun `getNextImage should increment index and return image from remote if local is empty`() =
        runTest {
            whenever(localDataSource.getImage(1)).thenReturn("image1")
            whenever(localDataSource.getImage(2)).thenReturn("")
            whenever(remoteDataSource.getImage(2)).thenReturn("remoteImage2")
            dogBreedManager.getImage()
            val result = dogBreedManager.getNextImage()
            assertEquals("remoteImage2", result)
            verify(localDataSource).getImage(1)
            verify(localDataSource).getImage(2)
            verify(remoteDataSource).getImage(2)
        }

    @Test
    fun `getPreviousImage should decrement index and return image from local`() = runTest {
        whenever(localDataSource.getImage(1)).thenReturn("image1")
        whenever(localDataSource.getImage(0)).thenReturn("image0")
        dogBreedManager.getImage()
        val result = dogBreedManager.getPreviousImage()
        assertEquals("image0", result)
        verify(localDataSource).getImage(1)
        verify(localDataSource).getImage(0)
    }

}