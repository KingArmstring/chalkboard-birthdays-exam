package com.chalkboardexam.birthdays.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils {
    companion object {

        fun hasNetworkAvailable(context: Context): Boolean {
            val service = Context.CONNECTIVITY_SERVICE
            val manager = context.getSystemService(service) as ConnectivityManager?
            val network = manager?.activeNetworkInfo
            return (network != null)
        }
    }
}