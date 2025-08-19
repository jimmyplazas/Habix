package dev.alejo.habix.habits.data.repository

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.alejo.habix.core.session.SessionManager
import dev.alejo.habix.habits.data.extension.toStartOfDayTimeStamp
import dev.alejo.habix.habits.data.local.HomeDao
import dev.alejo.habix.habits.data.mapper.toDomain
import dev.alejo.habix.habits.data.mapper.toDto
import dev.alejo.habix.habits.data.mapper.toEntity
import dev.alejo.habix.habits.data.mapper.toHabitSyncEntity
import dev.alejo.habix.habits.data.remote.ApiService
import dev.alejo.habix.habits.data.remote.util.resultOf
import dev.alejo.habix.habits.data.sync.HabitSyncWorker
import dev.alejo.habix.habits.domain.alarm.AlarmHandler
import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Duration
import java.time.ZonedDateTime

class HabitRepositoryImpl(
    private val dao: HomeDao,
    private val api: ApiService,
    private val alarmHandler: AlarmHandler,
    private val workManager: WorkManager,
    private val sessionManager: SessionManager
) : HabitRepository {

    override fun getAllHabitsForSelectedDate(
        date: ZonedDateTime
    ): Flow<List<Habit>> {
        val userId = sessionManager.getUserId()!!
        return dao
            .getHabitsForSelectedDate(userId, date.toStartOfDayTimeStamp())
            .map { it.map { habit -> habit.toDomain() } }
    }

    override suspend fun fetchHabitsFromApi() {
        resultOf {
            val userId = sessionManager.getUserId()!!
            val habits = api.getAllHabits(userId).toDomain(userId)
            insertHabits(habits)
        }
    }

    override suspend fun insertHabit(habit: Habit) {
        handleAlarm(habit)
        val userId = sessionManager.getUserId()!!
        dao.insertHabit(habit.toEntity())
        resultOf {
            api.insertHabit(userId, habit.toDto())
        }.onFailure {
            dao.insertHabitSync(habit.toHabitSyncEntity(userId))
        }
    }

    private suspend fun insertHabits(habits: List<Habit>) {
        habits.forEach { habit ->
            handleAlarm(habit)
            dao.insertHabit(habit.toEntity())
        }
    }

    override suspend fun getHabitById(habitId: String): Habit {
        val userId = sessionManager.getUserId()!!
        return dao.getHabitById(userId, habitId).toDomain()
    }

    private suspend fun handleAlarm(habit: Habit) {
        try {
            val userId = sessionManager.getUserId()!!
            val habit = dao.getHabitById(userId, habit.id)
            alarmHandler.cancel(habit.toDomain())
        } catch (e: Exception) { }
        alarmHandler.setRecurrentAlarm(habit)
    }

    override suspend fun syncHabits() {
        val worker = OneTimeWorkRequestBuilder<HabitSyncWorker>().setConstraints(
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        ).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5)).build()

        workManager.beginUniqueWork(
            "sync_habit_work",
            ExistingWorkPolicy.REPLACE,
            worker
        ).enqueue()
    }
}