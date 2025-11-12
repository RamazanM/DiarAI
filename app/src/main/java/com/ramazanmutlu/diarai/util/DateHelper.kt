package com.ramazanmutlu.diarai.util

import android.annotation.SuppressLint
import android.os.Build
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

@SuppressLint("SimpleDateFormat")
val todayLong: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    LocalDate.now().toEpochDay()
} else {
    val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")

    val today = Date()

    formatter.parse(formatter.format(today))?.time ?: Date().time
}