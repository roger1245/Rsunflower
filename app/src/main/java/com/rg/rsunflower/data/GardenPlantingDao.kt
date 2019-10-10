package com.rg.rsunflower.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Create by roger
 * on 2019/9/20
 */
@Dao
interface GardenPlantingDao {
    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId")
    fun getGardenPlanting(gardenPlantingId: Long): LiveData<GardenPlanting>

    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    fun getGardenPlantingForPlant(plantId: String): LiveData<GardenPlanting?>

    @Transaction
    @Query("SELECT * FROM plants")
    fun getPlantAndGardenPlantings(): LiveData<List<PlantAndGardenPlantings>>

    @Insert
    fun insetGardenPlanting(gardenPlanting: GardenPlanting): Long

    @Delete
    fun deleteGardenPlanting(gardenPlanting: GardenPlanting)



}