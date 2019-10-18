package com.rg.rsunflower.utilities

import android.content.Context
import com.rg.rsunflower.data.AppDatabase
import com.rg.rsunflower.data.HabitRepository
import com.rg.rsunflower.viewmodels.AddEditHabitViewModelFactory
import com.rg.rsunflower.viewmodels.GardenPlantingListViewModelFactory

/**
 * Create by roger
 * on 2019/9/20
 */
object InjectorUtils {
    private fun getGardenPlantingRepository(context: Context): HabitRepository {
        return HabitRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).habitDao()
        )
    }
    //public
    fun provideGardenPlantingListViewModelFactory(context: Context): GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }

    //public
    fun provideAddEditHabitFactory(context: Context): AddEditHabitViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return AddEditHabitViewModelFactory(repository)
    }


}