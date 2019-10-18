package com.rg.rsunflower.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Create by roger
 * on 2019/10/12
 */
@Dao
interface HabitDao {
    @Query("SELECT * FROM habit ORDER BY id")
    fun getHabits(): LiveData<List<Habit>>

    @Query("SELECT * FROM habit WHERE id = :id")
    fun getHabitById(id: Int): LiveData<Habit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(habit: List<Habit>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(habit: Habit)


    @Update
    fun update(habit: Habit)

    @Delete
    fun deleteHabit(habit: Habit)

    @Query("DELETE FROM habit WHERE id = :habitId")
    fun deleteHabit(habitId: Int)
}