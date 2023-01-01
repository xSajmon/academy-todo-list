package com.simon.academytodolist

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews.RemoteCollectionItems

class FileService: Service() {

    private val TAG = "FileService"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service started, updating data...")
        val items = intent?.getStringArrayListExtra("data")
//        FileHelper.writeData(items!!, applicationContext)
        SharedPreferencesHelper.writeData(applicationContext, items!!)

        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG,"Service stopped, data updated.")
        super.onDestroy()
    }

}