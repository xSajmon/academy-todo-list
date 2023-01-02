package com.simon.academytodolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simon.academytodolist.databinding.ItemListBinding

class ItemListAdapter(var items: ArrayList<Item>, val listener: (Item, Int) -> Unit): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    var filteredItems: ArrayList<Item> = ArrayList()

    fun setList(list: ArrayList<Item>, type: ListType){
        when(type) {
            ListType.ALL -> this.filteredItems = list
            ListType.ACTIVE -> this.filteredItems = list.filter { item -> item.isDeleted == false } as ArrayList<Item>
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = filteredItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = filteredItems.size

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Item){
            binding.itemName.text = item.text
            binding.deleteBtn.setOnClickListener{ listener(item, adapterPosition) }
        }
    }
}

