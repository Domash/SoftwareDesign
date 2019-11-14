package com.domash.helloworld

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "EVIL_ARTHAS"
        private const val PERMISSION_READ_PHONE_STATE = 228
    }

    private lateinit var textView: TextView
    private var content: String = "Device ID: ..."

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)

        val permission = checkSelfPermission(Manifest.permission.READ_PHONE_STATE)

        if(permission == PackageManager.PERMISSION_GRANTED) {

            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            content = "Device ID: " + telephonyManager.getDeviceId()

        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), PERMISSION_READ_PHONE_STATE)
        }
        
        content += "\nVersion: " + BuildConfig.VERSION_NAME
        textView.text = content

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(requestCode != PERMISSION_READ_PHONE_STATE) return

        permissions.forEachIndexed { index, permission ->

            if (PackageManager.PERMISSION_DENIED == grantResults[index]) return@forEachIndexed

            when (permission) {

                Manifest.permission.READ_PHONE_STATE -> {
                    val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    try {
                        content ="Device ID: " + telephonyManager.getDeviceId()
                    } catch (e: SecurityException) {
                        Log.i(TAG, e.toString())
                    }
                }

            }
        }

    }
}
