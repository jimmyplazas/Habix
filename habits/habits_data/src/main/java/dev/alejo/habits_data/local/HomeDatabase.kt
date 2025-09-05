package dev.alejo.habits_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.alejo.habits_data.local.entity.HabitEntity
import dev.alejo.habits_data.local.entity.HabitSyncEntity
import dev.alejo.habits_data.local.typeconverter.HomeTypeConverter

@Database(
    entities = [HabitEntity::class, HabitSyncEntity::class],
    version = 2
)
@TypeConverters(HomeTypeConverter::class)
abstract class HomeDatabase : RoomDatabase() {
    abstract val homeDao: HomeDao
}