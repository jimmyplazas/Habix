package dev.alejo.habix.habits.data.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.alejo.habix.R
import dev.alejo.habix.habits.data.extension.goAsync
import dev.alejo.habix.habits.domain.alarm.AlarmHandler
import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val HABIT_ID = "habit_id"
        const val HABIT_CHANNEL_ID = "habit_channel"
        const val HABIT_CHANNEL_NAME = "habit_channel_name"
    }

    @Inject
    lateinit var repository: HabitRepository

    @Inject
    lateinit var alarmHandler: AlarmHandler

    override fun onReceive(context: Context?, intent: Intent?) = goAsync {
        if (context == null || intent == null) return@goAsync
        val habitId = intent.getStringExtra(HABIT_ID) ?: return@goAsync
        val habit = repository.getHabitById(habitId)
        createNotificationChannel(context)
        if (!habit.completedDates.contains(LocalDate.now())) {
            showNotification(context, habit)
        }
        alarmHandler.setRecurrentAlarm(habit)
    }

    private fun showNotification(context: Context, habit: Habit) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, HABIT_CHANNEL_ID)
            .setContentTitle(habit.name)
            .setSmallIcon(R.drawable.ic_notification)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(habit.id.hashCode(), notification)
    }

    private fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            HABIT_CHANNEL_ID,
            HABIT_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Get your habits reminder"
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}