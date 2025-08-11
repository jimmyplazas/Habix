package dev.alejo.habix.habits.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import dev.alejo.habix.habits.data.extension.toTimeStamp
import dev.alejo.habix.habits.domain.alarm.AlarmHandler
import dev.alejo.habix.habits.domain.model.Habit
import java.time.DayOfWeek
import java.time.ZonedDateTime

class AlarmHandlerImpl(
    private val context: Context
) : AlarmHandler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun setRecurrentAlarm(habit: Habit) {
        val nextOccurrence = calculateNextOccurrence(habit)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextOccurrence.toTimeStamp(),
            createPendingIntent(habit, nextOccurrence.dayOfWeek)
        )
    }

    private fun calculateNextOccurrence(habit: Habit): ZonedDateTime {
        val today = ZonedDateTime.now()
        var nextOccurrence = ZonedDateTime.of(
            today.toLocalDate(),
            habit.reminder,
            today.zone
        )
        if (habit.frequency.contains(today.dayOfWeek) && today.isBefore(nextOccurrence)) {
            return nextOccurrence
        }
        do {
            nextOccurrence = nextOccurrence.plusDays(1)
        } while (!habit.frequency.contains(nextOccurrence.dayOfWeek))
        return nextOccurrence
    }

    private fun createPendingIntent(habit: Habit, dayOfWeek: DayOfWeek): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmReceiver.HABIT_ID, habit.id)
        }
        return PendingIntent.getBroadcast(
            context,
            habit.id.hashCode() * 10 + dayOfWeek.value,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    override fun cancel(habit: Habit) {
        val nextOccurrence = calculateNextOccurrence(habit)
        val pendingIntent = createPendingIntent(habit, nextOccurrence.dayOfWeek)
        alarmManager.cancel(pendingIntent)
    }
}