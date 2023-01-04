package com.simon.academytodolist

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.simon.academytodolist.databinding.ItemListBinding
import com.simon.academytodolist.databinding.ItemWebBinding
import kotlin.random.Random

class ItemListAdapter(var items: ArrayList<String>, val listener: (String, Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val urls = arrayListOf(
        "https://static.dw.com/image/64052597_905.jpg",
        "https://www.google.com/",
        "https://s3.viva.pl/newsy/grumpy-cat-najpopularniejszy-kot-instagrama-567749-GALLERY_BIG.jpg",
        "https://twitter.com/"
    )

    fun setList(list: ArrayList<String>){
        this.items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> WebViewHolder(ItemWebBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: String = items[position]
        when(holder){
            is ViewHolder -> holder.bind(item)
            is WebViewHolder -> holder.bind(urls.get(Random.nextInt(0, urls.size)))
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return if(position % 2 == 0) 0 else 1
    }

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: String){
            binding.itemName.text = item
            binding.deleteBtn.setOnClickListener{ listener(item, adapterPosition) }
        }
    }

    inner class WebViewHolder(private val binding: ItemWebBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(url: String){
            val webView = binding.webView
            webView.loadUrl(url)
        }
    }
}

