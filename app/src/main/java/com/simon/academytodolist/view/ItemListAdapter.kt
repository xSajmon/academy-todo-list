package com.simon.academytodolist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simon.academytodolist.utils.ItemComparator
import com.simon.academytodolist.R
import com.simon.academytodolist.databinding.ItemListBinding
import com.simon.academytodolist.models.Item


class ItemListAdapter(var items: ArrayList<Item>,
                      val listener: (Item, Int) -> Unit): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    var sortType: ItemComparator? = null
    var listType = ListType.ACTIVE


    fun setList(list: ArrayList<Item>){
        updateList(list)
        notifyDataSetChanged()
    }

    private fun updateList(list: ArrayList<Item>){
        var sortedList = list
        if(sortType != null){
            sortedList = ArrayList(list.sortedWith(sortType!!))
        }
        when(listType) {
            ListType.ALL -> this.items = sortedList
            ListType.ACTIVE -> this.items = sortedList.filter { item -> item.isDeleted == false } as ArrayList<Item>
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Item){
            binding.itemName.text = item.text
            binding.deleteBtn.setOnClickListener{ listener(item, adapterPosition) }
            if(item.isDeleted == true){
                binding.itemName.alpha = 0.5f
                binding.deleteBtn.setBackgroundResource(R.drawable.ic_restore)
            } else {
                binding.itemName.alpha = 1.0f
                binding.deleteBtn.setBackgroundResource(R.drawable.ic_delete)
            }

        }
    }
}

