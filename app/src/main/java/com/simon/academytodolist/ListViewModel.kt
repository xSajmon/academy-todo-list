package com.simon.academytodolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {

    private val _itemList = MutableLiveData<ArrayList<Item>>()
    val itemList: LiveData<ArrayList<Item>> get() = _itemList

    fun load(list: ArrayList<Item>){
        _itemList.value = list
    }

    fun addItem(text: String){
        val updated = _itemList.value
        updated?.add(Item(id = updated.size, text = text))
        _itemList.value = updated!!
    }

    fun deleteItem(position: Int){
        _itemList.value?.get(position)!!.isDeleted = true
        _itemList.value = _itemList.value
    }

}