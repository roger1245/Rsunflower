package com.rg.rsunflower.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rg.rsunflower.data.Habit
import com.rg.rsunflower.data.HabitRepository

/**
 * Create by roger
 * on 2019/10/20
 */
class MemoryCurveViewModel internal constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {
    var habits: LiveData<List<Habit>> = habitRepository.getHabits()

    var size: Int? = null

    val topCardIndex: MutableLiveData<Int> = MutableLiveData()
    val bottomCardIndex: MutableLiveData<Int> = MutableLiveData()

    fun upDataIndex() {
        size?.let {
            if (it > 1) {
                val topI = topCardIndex.value
                val bottomI = bottomCardIndex.value
                val sizeCopy = size

                if (topCardIndex.value == null) {
                    topCardIndex.postValue(0)
                    bottomCardIndex.postValue(1)
                } else if (bottomI != null && sizeCopy != null && bottomI + 1 < sizeCopy) {
                    topCardIndex.postValue((topI ?: 0) + 1)
                    bottomCardIndex.postValue((bottomI ?: 0) + 1)
                }
            }

        }
    }
}