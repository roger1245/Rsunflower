package com.rg.rsunflower.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Create by roger
 * on 2019/9/23
 */
class PlantAndGardenPlantings {
    @Embedded
    lateinit var plant: Plant

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    var gardenPlantings: List<GardenPlanting> = arrayListOf()
}