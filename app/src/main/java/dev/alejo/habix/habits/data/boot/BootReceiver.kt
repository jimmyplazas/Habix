package dev.alejo.habix.habits.data.boot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import dev.alejo.core_data.session.SessionManager
import dev.alejo.habix.habits.data.extension.goAsync
import dev.alejo.habix.habits.data.local.HomeDao
import dev.alejo.habix.habits.data.mapper.toDomain
import dev.alejo.habix.habits.domain.alarm.AlarmHandler
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