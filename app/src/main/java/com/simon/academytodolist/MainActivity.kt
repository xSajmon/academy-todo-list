package com.simon.academytodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.simon.academytodolist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var item: EditText
    private lateinit var add: Button
    private lateinit var listView: ListView
    private var itemList: ArrayList<String> = arrayListOf()
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private val listViewModel: ListViewModel by viewModels()

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

        listViewModel.load(itemList)
        listViewModel.itemList.observe(this) {
            itemList = it
            arrayAdapter.notifyDataSetChanged()
            FileHelper.writeData(it, applicationContext)
        }

        add.setOnClickListener{
            val newItem = item.text.toString()
            listViewModel.addItem(newItem)
            item.text.clear()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val dialog = DeleteItemDialogFragment()
            val bundle = Bundle()
            bundle.putString("item", itemList[position])
            bundle.putInt("itemPosition", position)
            dialog.arguments = bundle
            dialog.show(supportFragmentManager, DeleteItemDialogFragment.TAG)
        }
    }

}