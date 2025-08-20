package dev.alejo.habix.habits.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.alejo.core_data.session.SessionManager
import dev.alejo.habix.habits.data.local.HomeDao
import dev.alejo.habix.habits.data.local.entity.HabitSyncEntity
import dev.alejo.habix.habits.data.mapper.toDomain
import dev.alejo.habix.habits.data.mapper.toDto
import dev.alejo.habix.habits.data.remote.ApiService
import dev.alejo.habix.habits.data.remote.util.resultOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@HiltWorker
class HabitSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val dao: HomeDao,
    private val api: ApiService,
    private val sessionManager: SessionManager
) : CoroutineWorker(context, params) {

    private val userId = sessionManager.getUserId()!!

    override suspend fun doWork(): Result {
        if (runAttemptCount > 3) {
            return Result.failure()
        }
        val habits = dao.getAllHabitsSync(userId)
        return try {
            val jobs = supervisorScope {
                habits.map { habit -> launch { sync(habit) } }
            }
            jobs.forEach { it.join() }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun sync(habitSync: HabitSyncEntity) {
        val habit = dao.getHabitById(userId, habitSync.id)
        val token = sessionManager.getUserToken()!!
        resultOf {
            api.insertHabit(userId = userId, token = token, habit = habit.toDomain().toDto())
        }.onSuccess {
            dao.deleteHabitSync(habitSync)
        }.onFailure {
            throw it
        }
    }
}