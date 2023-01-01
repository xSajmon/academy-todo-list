package com.simon.academytodolist

import android.content.Context

class SharedPreferencesHelper {

    companion object{

        private const val FILE_NAME = "prefs"

        fun writeData(context: Context, items: ArrayList<String>){
            val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val set = HashSet<String>()
            set.addAll(items)
            sharedPreferences.edit().putStringSet("items", set).apply()
        }

        fun readData(context: Context): ArrayList<String>{
            val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val set = sharedPreferences.getStringSet("items", emptySet())
            val items = ArrayList<String>()
            items.addAll(set!!)
            return items
        }


    }


}