package com.simon.academytodolist.utils

import android.content.Context
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.simon.academytodolist.models.Item
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class SharedPreferencesHelper {

    companion object{

        private const val FILE_NAME = "prefs"
        private const val ITEMS = "items"
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

        fun writeData(context: Context, items: ArrayList<String>){
            val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val gson = Gson().newBuilder()
            gson.registerTypeAdapter(ZonedDateTime::class.java, serializer())
            val json = gson.create().toJson(items)
            sharedPreferences.edit().putString(ITEMS, json).apply()
        }

        fun readData(context: Context): ArrayList<Item>{
            val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val items = sharedPreferences.getString(ITEMS, null) ?: return ArrayList()
            val gson = Gson().newBuilder()
            gson.registerTypeAdapter(ZonedDateTime::class.java, deserializer())
            return gson.create().fromJson(items, object: TypeToken<ArrayList<Item>>(){}.type)

        }

        private fun serializer(): JsonSerializer<ZonedDateTime>{
            return JsonSerializer { src, _, _ ->
                JsonPrimitive(formatter.format(src))
            }
        }

        private fun deserializer(): JsonDeserializer<ZonedDateTime>{
            return JsonDeserializer { src, _, _ ->
                ZonedDateTime.parse(src?.asString, formatter)
            }
        }

    }
}