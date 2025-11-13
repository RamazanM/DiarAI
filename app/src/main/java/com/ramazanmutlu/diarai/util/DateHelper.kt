package com.ramazanmutlu.diarai.util

import java.util.Calendar
import java.util.TimeZone

fun getStartOfTodayTimestamp(): Long {
    // Use a Calendar instance set to the UTC timezone
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

    // Reset the time to the beginning of the day
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    // Return the time in milliseconds
    return calendar.time.time
}
