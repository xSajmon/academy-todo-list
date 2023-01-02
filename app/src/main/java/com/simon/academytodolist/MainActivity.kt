package com.simon.academytodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simon.academytodolist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var item: EditText
    private lateinit var add: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var listAdapter: ItemListAdapter
    private var itemList: ArrayList<Item> = arrayListOf()
    private val listViewModel: ListViewModel by viewModels()
    private var listType = ListType.ACTIVE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = binding.editText
        add = binding.button
        recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        listAdapter = ItemListAdapter(ArrayList(), listType) { item, _ ->
            confirm(item)
        }

        itemList = SharedPreferencesHelper.readData(applicationContext)

        listViewModel.load(itemList)
        listViewModel.itemList.observe(this) {
            listAdapter.setList(it)
            recyclerView.adapter = listAdapter
            val i = Intent(this, FileService::class.java)
            i.putExtra("data", it)
            startService(i)
        }

        add.setOnClickListener{
            val newItem = item.text.toString()
            listViewModel.addItem(newItem)
            item.text.clear()
        }

        binding.mode.setOnClickListener{
            listType = if(listType == ListType.ACTIVE) ListType.ALL else ListType.ACTIVE
            listAdapter.type = listType
            listAdapter.setList(itemList)
        }
    }

    private fun confirm(item: Item) {
        val dialog = DeleteItemDialogFragment()
        val bundle = Bundle()
        bundle.putString("item", item.text)
        bundle.putInt("itemPosition", item.id)
        dialog.arguments = bundle
        dialog.show(supportFragmentManager, DeleteItemDialogFragment.TAG)
    }

}