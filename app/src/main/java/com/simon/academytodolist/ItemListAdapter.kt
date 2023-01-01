package com.simon.academytodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simon.academytodolist.databinding.ItemListBinding

class ItemListAdapter(var items: ArrayList<String>, val listener: (String, Int) -> Unit): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    fun setList(list: ArrayList<String>){
        this.items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: String){
            binding.itemName.text = item
            binding.deleteBtn.setOnClickListener{ listener(item, adapterPosition) }
        }
    }
}

