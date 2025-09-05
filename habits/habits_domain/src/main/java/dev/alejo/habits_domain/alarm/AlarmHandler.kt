package dev.alejo.habits_domain.alarm

import dev.alejo.habits_domain.model.Habit

interface AlarmHandler {
    fun setRecurrentAlarm(habit: Habit)
    fun cancel(habit: Habit)
}