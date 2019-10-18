package com.rg.rsunflower.data

import android.content.Context
import androidx.room.*
import com.rg.rsunflower.utilities.DATABASE_NAME

/**
 * Create by roger
 * on 2019/10/12
 */
@Database(entities = [Habit::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class, StateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao



    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}