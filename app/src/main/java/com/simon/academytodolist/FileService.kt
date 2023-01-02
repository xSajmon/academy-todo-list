package com.simon.academytodolist

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class FileService: Service() {

    private val TAG = "FileService"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service started, updating data...")
        val items = intent?.getStringArrayListExtra("data")
//        FileHelper.writeData(items!!, applicationContext)
        if (items != null) {
            SharedPreferencesHelper.writeData(applicationContext, items)
        }
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG,"Service stopped, data updated.")
        super.onDestroy()
    }

}