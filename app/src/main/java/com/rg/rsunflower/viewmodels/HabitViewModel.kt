package com.rg.rsunflower.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.rsunflower.data.Habit
import com.rg.rsunflower.data.HabitRepository
import com.rg.rsunflower.data.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Create by roger
 * on 2019/9/1
 */
class HabitViewModel internal constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {
    var habits: LiveData<List<Habit>> = habitRepository.getHabits()


    fun upDateHabit() {
        habits = habitRepository.getHabits()
    }


    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            habitRepository.createHabit(habit)
            upDateHabit()
        }
    }

    fun delete(habitId: Int) {
        viewModelScope.launch {
            habitRepository.deleteHabitById(habitId)
            upDateHabit()
        }

    }



}