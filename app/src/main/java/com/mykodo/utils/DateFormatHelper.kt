package com.mykodo.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateFormatHelper @Inject constructor() {
    fun formatDateRelative(date: Date): String {
        val now = System.currentTimeMillis()
        val timeDiff = date.time - now

        return when {
            DateUtils.isToday(date.time) -> "Today"
            DateUtils.isToday(date.time + DateUtils.DAY_IN_MILLIS) -> "Yesterday"
            DateUtils.isToday(date.time - DateUtils.DAY_IN_MILLIS) -> "Tomorrow"
            timeDiff in 0..DateUtils.HOUR_IN_MILLIS * 2 -> "In ${timeDiff / DateUtils.HOUR_IN_MILLIS} hours"
            else -> {
                // For other cases, use a SimpleDateFormat
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                dateFormat.format(date)
            }
        }
    }
}