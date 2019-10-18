package com.rg.rsunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rg.rsunflower.data.HabitRepository

/**
 * Create by roger
 * on 2019/10/14
 */
class AddEditHabitViewModelFactory(
    private val repository: HabitRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddEditHabitViewModel(repository) as T
    }
}