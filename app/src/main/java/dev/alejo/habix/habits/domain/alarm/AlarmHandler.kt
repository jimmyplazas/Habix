package dev.alejo.habix.habits.domain.alarm

import dev.alejo.habix.habits.domain.model.Habit

interface AlarmHandler {
    fun setRecurrentAlarm(habit: Habit)
    fun cancel(habit: Habit)
}