package com.chalkboardexam.birthdays.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        fun formatDate(date: String) : String {
            val inputDateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)

            val inputDate = try {
                val trimmedDate = date.substring(startIndex = 0, endIndex = date.indexOf('T'))
                inputDateFormat.parse(trimmedDate)
            }catch (e: Exception) {
                return "Invalid date"
            }

            val desiredFormat = SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
            return desiredFormat.format(inputDate)
        }
    }
}