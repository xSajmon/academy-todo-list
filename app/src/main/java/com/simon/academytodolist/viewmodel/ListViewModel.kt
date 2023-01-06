package com.simon.academytodolist.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.simon.academytodolist.source.AppDatabase
import com.simon.academytodolist.source.ItemRepository
import com.simon.academytodolist.view.SortType
import com.simon.academytodolist.models.Item
import kotlinx.coroutines.launch


class ListViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ItemRepository
    var itemList: LiveData<List<Item>>
    private val filter = MutableLiveData<Pair<Boolean, SortType>>()
    init {
        val dao = AppDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(dao)
        filter.value = Pair(false, SortType.DEFAULT)
        itemList = Transformations.switchMap(
            filter) {
            repository.findAll(it.first, it.second)}
    }

    fun addItem(text: String){
        viewModelScope.launch {
            repository.addItem(Item(text = text))
        }
    }

    fun changeItemState(index: Int){
        viewModelScope.launch {
            repository.changeState(index)
        }
    }
    fun changeMode(){
        val first = filter.value!!.first
        val updated = filter.value!!.copy(!first)
        filter.postValue(updated)
    }

    fun sortBy(sortType: SortType){
        val updated = filter.value!!.copy(second = sortType)
        filter.postValue(updated)
    }



}