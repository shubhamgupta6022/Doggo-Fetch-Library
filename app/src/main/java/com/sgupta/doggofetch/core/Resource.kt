package com.sgupta.doggofetch.core

sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    object Loading : Resource<Nothing>()
}