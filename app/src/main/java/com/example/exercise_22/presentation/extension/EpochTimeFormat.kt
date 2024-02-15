package com.example.exercise_22.presentation.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun realTimeConvert(epochTime: Int): String {
    val date = Date(epochTime.toLong())
    val dateFormat = SimpleDateFormat("dd MMMM hh:mm a", Locale.ENGLISH)

    return dateFormat.format(date)
}

