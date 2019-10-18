package com.rg.rsunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Create by roger
 * on 2019/10/12
 */
@Entity(
    tableName = "habit"
)
data class Habit(
    @ColumnInfo(name = "habit_name")val habitName: String,
    @ColumnInfo(name = "level")val level: State = State.One,
    @ColumnInfo(name = "last_watering_date") val lastWateringDate: Calendar = Calendar.getInstance()
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var habitId: Int = 0
}
//Ebbinghaus
enum class State {
    One, Two, Four, Seven, Fifteen, Success
}