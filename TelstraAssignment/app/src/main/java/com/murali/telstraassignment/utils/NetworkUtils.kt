package com.murali.telstraassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network

fun Context.isActiveNetworkAvailable(): Boolean{
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: Network? = connectivityManager.activeNetwork
    return activeNetwork != null
}