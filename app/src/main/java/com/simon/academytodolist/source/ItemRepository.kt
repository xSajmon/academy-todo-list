package com.simon.academytodolist.source

import androidx.lifecycle.LiveData
import com.simon.academytodolist.view.SortType
import com.simon.academytodolist.models.Item
import java.time.ZonedDateTime

class ItemRepository(private val itemDao: ItemDao) {

    fun findAll(boolean: Boolean, sortType: SortType): LiveData<List<Item>> = itemDao.getItems(boolean, sortType.type)

    suspend fun addItem(item: Item){
        itemDao.addItem(item)
    }

    suspend fun changeState(id: Int){
        val item = findItemById(id)
        item.isDeleted = !item.isDeleted!!
        if(!item.isDeleted!!) {
            item.data = ZonedDateTime.now()
        }
        itemDao.update(item)
    }

    private suspend fun findItemById(id: Int): Item{
        return itemDao.findItemById(id)
    }
}