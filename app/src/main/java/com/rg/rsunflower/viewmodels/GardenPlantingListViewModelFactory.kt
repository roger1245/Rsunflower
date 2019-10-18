package com.rg.rsunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rg.rsunflower.data.HabitRepository

/**
 * Create by roger
 * on 2019/10/12
 */
class GardenPlantingListViewModelFactory(
    private val repository: HabitRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HabitViewModel(repository) as T
    }
}