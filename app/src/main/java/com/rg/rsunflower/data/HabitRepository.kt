package com.rg.rsunflower.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Create by roger
 * on 2019/10/12
 */
class HabitRepository private constructor(
    private val habitDao: HabitDao
){

    suspend fun createHabit(habit: Habit) {
        withContext(Dispatchers.IO) {
            habitDao.insert(habit)
        }
    }

    suspend fun removeHabit(habit: Habit) {
        withContext(Dispatchers.IO) {
            habitDao.deleteHabit(habit)
        }
    }

    suspend fun deleteHabitById(habitId: Int) {
        withContext(Dispatchers.IO) {
            habitDao.deleteHabit(habitId)
        }
    }

    suspend fun upDateHabit(habit: Habit) {
        withContext(Dispatchers.IO) {
            habitDao.update(habit)
        }

    }





    fun getHabits() = habitDao.getHabits()

    fun getHabitById(id: Int) = habitDao.getHabitById(id)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: HabitRepository? = null

        fun getInstance(habitDao: HabitDao) =
            instance ?: synchronized(this) {
                instance ?: HabitRepository(habitDao).also { instance = it }
            }
    }
}