package dev.alejo.habits_data.boot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import dev.alejo.core_domain.session.SessionManager
import dev.alejo.habits_data.extension.goAsync
import dev.alejo.habits_data.local.HomeDao
import dev.alejo.habits_data.mapper.toDomain
import dev.alejo.habits_domain.alarm.AlarmHandler
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var dao: HomeDao

    @Inject
    lateinit var alarmHandler: AlarmHandler

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        if (context == null || intent == null) return@goAsync
        if (intent.action != "android.intent.action.BOOT_COMPLETED") return@goAsync
        val userId = sessionManager.getUserId() ?: return@goAsync

        val habits = dao.getAllHabits(userId)
        habits.forEach { habit ->
            alarmHandler.setRecurrentAlarm(habit.toDomain())
        }
    }
}