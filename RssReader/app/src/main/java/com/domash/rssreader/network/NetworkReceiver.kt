package com.domash.rssreader.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

class NetworkReceiver : BroadcastReceiver() {

    enum class NetworkStatus {
        NOT_CONNECTED,
        MOBILE_DATA,
        WIFI
    }

    override fun onReceive(context: Context, intent: Intent) {

        val networkStatus = getStatus(context)
        val networkStatusStr = getStatusString(context)

        Toast.makeText(context, networkStatusStr, Toast.LENGTH_LONG).show();

    }

    companion object ConnectivityStatusUtil {

        fun getStatus(context: Context) : NetworkStatus {

            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val networkState: NetworkInfo? = cm.activeNetworkInfo

            networkState?.let {
                return when(networkState.type) {
                    ConnectivityManager.TYPE_WIFI -> NetworkStatus.WIFI
                    ConnectivityManager.TYPE_MOBILE -> NetworkStatus.MOBILE_DATA
                    else -> NetworkStatus.NOT_CONNECTED
                }
            }

            return NetworkStatus.NOT_CONNECTED
        }

        fun getStatusString(context: Context): String = when(getStatus(context)) {
            NetworkStatus.WIFI -> "Wifi"
            NetworkStatus.MOBILE_DATA -> "Mobile data"
            NetworkStatus.NOT_CONNECTED -> "Not connected"
        }

    }

}