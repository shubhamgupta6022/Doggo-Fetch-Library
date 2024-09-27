package com.sgupta.doggofetch.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sgupta.doggofetch.data.converters.Converters
import com.sgupta.doggofetch.data.dao.DogBreedDao
import com.sgupta.doggofetch.data.dao.DogBreedListDao
import com.sgupta.doggofetch.data.entities.DogBreedImage
import com.sgupta.doggofetch.data.entities.DogBreedList

@Database(entities = [DogBreedImage::class, DogBreedList::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class DoggoDatabase : RoomDatabase(), DoggoDatabaseProvider {
    abstract fun dogBreedDao(): DogBreedDao
    abstract fun dogBreedListDao(): DogBreedListDao

    companion object {
        @Volatile
        private var INSTANCE: DoggoDatabase? = null

        fun getDatabase(context: Context): DoggoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DoggoDatabase::class.java,
                    "dog_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}