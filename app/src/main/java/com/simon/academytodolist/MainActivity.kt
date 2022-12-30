package com.simon.academytodolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.simon.academytodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var item: EditText
    private lateinit var add: Button
    private lateinit var listView: ListView
    private var itemList: ArrayList<String> = arrayListOf()
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = binding.editText
        add = binding.button
        listView = binding.list


        itemList = FileHelper.readData(this)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            itemList)

        listView.adapter = arrayAdapter

        add.setOnClickListener{
            val newItem = item.text.toString()
            itemList.add(newItem)
            item.text.clear()
            FileHelper.writeData(itemList, applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }
        
        listView.setOnItemClickListener { _, _, position, _ ->
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item from the list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }
            alert.setPositiveButton("Yes") { dialogInterface, _ ->
                itemList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                FileHelper.writeData(itemList, applicationContext)
                dialogInterface.cancel()
            }
            alert.show()
        }
    }

}