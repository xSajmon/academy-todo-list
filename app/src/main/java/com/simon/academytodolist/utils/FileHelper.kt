package com.simon.academytodolist.utils

import android.content.Context
import android.widget.Toast
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {

    companion object{
        private const val FILENAME = "listinfo.dat"

        fun writeData(item: ArrayList<String>, context: Context){
            var oas: ObjectOutputStream? = null
            var fos: FileOutputStream? = null
            try {
                fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE) // this method automatically creates file if it does not exits
                oas = ObjectOutputStream(fos)
                oas.writeObject(item)
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            } finally {
                fos?.close()
                oas?.close()
            }
        }

        fun readData(context: Context): ArrayList<String>{
            var itemList: ArrayList<String> = ArrayList()
            var ois: ObjectInputStream? = null
            var fis: FileInputStream? = null
            try {
                fis = context.openFileInput(FILENAME)
                ois = ObjectInputStream(fis)
                itemList = ois.readObject() as ArrayList<String>
            } catch (e: FileNotFoundException){
                e.printStackTrace()
                Toast.makeText(context, "File does not exists.", Toast.LENGTH_LONG).show()
            }
            finally {
                fis?.close()
                ois?.close()
            }
            return itemList
        }

    }



}