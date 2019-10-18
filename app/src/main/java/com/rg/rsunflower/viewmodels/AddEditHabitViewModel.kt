package com.rg.rsunflower.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rg.rsunflower.data.Habit
import com.rg.rsunflower.data.HabitRepository
import com.rg.rsunflower.data.State
import kotlinx.coroutines.launch
import java.util.*

/**
 * Create by roger
 * on 2019/10/14
 */
class AddEditHabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {
    val habitName = MutableLiveData<String>()

    val state = MutableLiveData<State>()

    //页面跳转的被观察量
    private val _habitUpdatedEvent = MutableLiveData<Int>()
    val habitUpdatedEvent: LiveData<Int> = _habitUpdatedEvent

    private var isNewHabit: Boolean = false
    private var habitId: Int? = null

    fun start(habitId: Int?) {

        if (habitId == null) {
            isNewHabit = true
            return
        }
        viewModelScope.launch {
            onHabitLoaded(repository.getHabitById(habitId))
        }
    }

    fun saveHabit() {

        val currentHabitName   = habitName.value

        if (currentHabitName != null && currentHabitName.isNotEmpty()) {

            if (isNewHabit || habitId == null) {
                val habit = Habit(currentHabitName, lastWateringDate =  Calendar.getInstance())
                createHabit(habit)



            } else {
                val habit = Habit(currentHabitName, state.value ?: State.One, Calendar.getInstance())
                updateHabit(habit)
            }
        }
    }

    private fun onHabitLoaded(habit: LiveData<Habit>) {
        habit.value?.let {
            habitName.postValue(it.habitName)
        }
    }

    private fun createHabit(habit: Habit) = viewModelScope.launch{
        repository.createHabit(habit)
        _habitUpdatedEvent.value = -1

    }

    private fun updateHabit(habit: Habit) = viewModelScope.launch{
        repository.upDateHabit(habit)
        _habitUpdatedEvent.value = -1
    }


}