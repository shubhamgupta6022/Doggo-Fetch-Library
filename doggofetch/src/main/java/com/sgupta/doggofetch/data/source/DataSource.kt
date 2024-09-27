package com.sgupta.doggofetch.data.source

internal interface DataSource {
    suspend fun getImage(index: Int): String
    suspend fun getImages(number: Int): List<String>
}