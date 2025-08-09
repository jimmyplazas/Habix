package dev.alejo.habix.habits.data.extension

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

fun ZonedDateTime.toStartOfDayTimeStamp() = truncatedTo(ChronoUnit.DAYS).toEpochSecond() * 1000

fun Long.toZonedDateTime(): ZonedDateTime = ZonedDateTime.ofInstant(
    Instant.ofEpochMilli(this),
    ZoneId.systemDefault()
)

fun ZonedDateTime.toTimeStamp(): Long = this.toInstant().toEpochMilli()

fun LocalDate.toZonedDateTime(): ZonedDateTime = this.atStartOfDay(ZoneId.systemDefault())

fun LocalTime.toZonedDateTime(): ZonedDateTime = this.atDate(LocalDate.now())
    .atZone(ZoneId.systemDefault())