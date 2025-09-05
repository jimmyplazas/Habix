package dev.alejo.habits_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.alejo.habits_data.local.entity.HabitEntity
import dev.alejo.habits_data.local.entity.HabitSyncEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    @Query("SELECT * FROM HabitEntity WHERE userId = :userId")
    fun getAllHabits(userId: String): List<HabitEntity>

    @Query("SELECT * FROM HabitEntity WHERE id = :id AND userId = :userId")
    suspend fun getHabitById(userId: String, id: String): HabitEntity

    @Query("SELECT * FROM HabitEntity WHERE userId = :userId AND startDate <= :date")
    fun getHabitsForSelectedDate(userId: String, date: Long): Flow<List<HabitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitSync(habitSync: HabitSyncEntity)

    @Query("SELECT * FROM HabitSyncEntity WHERE userId = :userId")
    fun getAllHabitsSync(userId: String): List<HabitSyncEntity>

    @Delete
    suspend fun deleteHabitSync(habitSync: HabitSyncEntity)

}