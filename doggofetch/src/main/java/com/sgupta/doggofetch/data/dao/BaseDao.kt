package com.sgupta.doggofetch.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
internal interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceItem(t: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceAll(tList: List<T>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreItem(t: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreAll(tList: List<T>): List<Long>

    @Update
    suspend fun updateItem(t: T)

    @Update
    suspend fun updateAll(tList: List<T>)

    @Delete
    suspend fun deleteItem(t: T)

    @Delete
    suspend fun deleteAll(tList: List<T>)
}