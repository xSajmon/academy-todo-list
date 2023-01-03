package com.simon.academytodolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simon.academytodolist.databinding.ItemListBinding


class ItemListAdapter(var items: ArrayList<Item>, var type: ListType, val listener: (Item, Int) -> Unit): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    fun setList(list: ArrayList<Item>){
        updateList(list)
        notifyDataSetChanged()
    }

    fun updateList(list: ArrayList<Item>){
        when(this.type) {
            ListType.ALL -> this.items = list
            ListType.ACTIVE -> this.items = list.filter { item -> item.isDeleted == false } as ArrayList<Item>
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

