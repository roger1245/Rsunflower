package com.rg.rsunflower.data

import androidx.room.TypeConverter
import java.lang.Exception
import java.util.*

/**
 * Create by roger
 * on 2019/10/13
 */
class Converters {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }


}
class StateConverters {
    @TypeConverter
    fun stateToInt(state: State): Int =
        when (state) {
            State.One -> 1
            State.Two -> 2
            State.Four -> 3
            State.Seven -> 4
            State.Fifteen -> 5
            State.Success -> 6
        }
    @TypeConverter
    fun intToState(value: Int): State =
        when (value) {
            1 -> State.One
            2 -> State.Two
            3 -> State.Four
            4 -> State.Seven
            5 -> State.Fifteen
            6 -> State.Success

            else -> throw Exception("not support")
        }
}