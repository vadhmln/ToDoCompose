package ru.vdh.todocompose.common.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {
    fun getCurrentDate(currentTime: Long): String? {
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(currentTime)
    }
}