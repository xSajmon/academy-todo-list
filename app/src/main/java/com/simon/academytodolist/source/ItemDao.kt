package com.simon.academytodolist.source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.simon.academytodolist.models.Item

@Dao
interface ItemDao {


    @Query("SELECT * FROM item WHERE CASE " +
            "WHEN :bool = 0 THEN isDeleted = :bool ELSE isDeleted = isDeleted END ORDER BY " +
            "(CASE WHEN :type = 0 THEN id END)ASC, " +
            "(CASE WHEN :type = 1 THEN LENGTH(text)END)DESC," +
            "(CASE WHEN :type = 2 THEN data END) DESC")
    fun getItems(bool: Boolean, type: Int): LiveData<List<Item>>

    @Query("SELECT * FROM item WHERE item.id = :id")
    suspend fun findItemById(id: Int): Item

    @Insert
    suspend fun addItem(item: Item)

    @Update
    suspend fun update(item: Item)
}