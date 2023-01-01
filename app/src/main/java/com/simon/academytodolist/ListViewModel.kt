package com.simon.academytodolist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.log

class ListViewModel: ViewModel() {

    private val _itemList = MutableLiveData<ArrayList<String>>()
    val itemList: LiveData<ArrayList<String>> get() = _itemList

    fun load(list: ArrayList<String>){
        _itemList.value = list
    }

    fun addItem(item: String){
        _itemList.value?.add(item)
        _itemList.value = _itemList.value
    }

    fun deleteItem(position: Int){
        _itemList.value?.removeAt(position)
        _itemList.value = _itemList.value
    }

}