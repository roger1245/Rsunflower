package com.rg.rsunflower.data

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Create by roger
 * on 2019/9/20
 */
class GardenPlantingRepository constructor(
    private val gardenPlantingDao: GardenPlantingDao
){

    suspend fun createGardenPlanting(plantId: String) {
        withContext(IO) {
            val gardenPlanting = GardenPlanting(plantId)
            gardenPlantingDao.insetGardenPlanting(gardenPlanting)
        }
    }

    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting) {
        withContext(IO) {
            gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
        }
    }

    //查询已有的GardenPlanting， 通过plantId
    fun getGardenPlantingForPlant(plantId: String) =gardenPlantingDao.getGardenPlantingForPlant(plantId)
    //查询已有的GardenPlanting
    fun getGardenPlantings() = gardenPlantingDao.getGardenPlantings()

    //同时查询GardenPlanting和Plant两张表
    fun getPlantAndGardenPlantings() = gardenPlantingDao.getPlantAndGardenPlantings()


    //单例模式
    companion object {
        @Volatile private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao) =
                instance ?: synchronized(this) {
                    instance ?: GardenPlantingRepository(gardenPlantingDao).also { instance = it }
                }
    }

}