package dev.alejo.habits_data.local.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class HomeTypeConverter {

    @TypeConverter
    fun fromFrequency(days: List<Int>): String = joinIntoString(days)

    @TypeConverter
    fun toFrequency(value: String): List<Int> = splitToIntList(value)

    @TypeConverter
    fun fromCompletedDates(dates: List<Long>): String = joinIntoString(dates)

    @TypeConverter
    fun toCompletedDates(value: String): List<Long> = splitToLongList(value)

    // Private helpers
    private fun joinIntoString(list: List<Number>): String =
        list.joinToString(",")

    private fun splitToIntList(value: String?): List<Int> =
        value?.split(",")?.mapNotNull {
            try {
                it.toInt()
            } catch (ex: NumberFormatException) {
                null
            }
        } ?: emptyList()

    private fun splitToLongList(value: String?): List<Long> =
        value?.split(",")?.mapNotNull {
            try {
                it.toLong()
            } catch (ex: NumberFormatException) {
                null
            }
        } ?: emptyList()

}