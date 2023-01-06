package com.simon.academytodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simon.academytodolist.databinding.ActivityMainBinding
import com.simon.academytodolist.fragments.DeleteItemDialogFragment
import com.simon.academytodolist.models.Item
import com.simon.academytodolist.view.ItemListAdapter
import com.simon.academytodolist.view.SortType
import com.simon.academytodolist.viewmodel.ListViewModel


class MainActivity : AppCompatActivity(), OnClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var item: EditText
    private lateinit var add: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var listAdapter: ItemListAdapter
    private var itemList: List<Item> = listOf()
    private lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        item = binding.editText
        add = binding.button
        recyclerView = binding.list
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        listAdapter = ItemListAdapter(ArrayList()) { item, _ ->
            if(!item.isDeleted!!){
                confirm(item)
            } else {
                restore(item)
            }
        }

        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        listViewModel.itemList.observe(this) {
            listAdapter.setList(it)
            recyclerView.adapter = listAdapter
        }

        add.setOnClickListener{
            val newItem = item.text.toString()
            listViewModel.addItem(newItem)
            item.text.clear()
        }

        binding.modeBtn.setOnClickListener(this)
        binding.sortByTextBtn.setOnClickListener(this)
        binding.sortByDateBtn.setOnClickListener(this)
        binding.sortByPosition.setOnClickListener(this)

    }

    private fun restore(item: Item) {
        listViewModel.changeItemState(item.id)
        Toast.makeText(this, "${item.text} has been restored", Toast.LENGTH_SHORT).show()
    }

    private fun confirm(item: Item) {
        val dialog = DeleteItemDialogFragment()
        val bundle = Bundle()
        bundle.putString("item", item.text)
        bundle.putInt("itemPosition", item.id)
        dialog.arguments = bundle
        dialog.show(supportFragmentManager, DeleteItemDialogFragment.TAG)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.modeBtn -> listViewModel.changeMode()
            R.id.sortByTextBtn -> listViewModel.sortBy(SortType.BY_TEXT_LENGTH)
            R.id.sortByPosition -> listViewModel.sortBy(SortType.DEFAULT)
            R.id.sortByDateBtn -> listViewModel.sortBy(SortType.BY_DATE)
        }
    }


}