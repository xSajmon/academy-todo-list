package com.simon.academytodolist.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.ZonedDateTime

@Entity
data class Item(
                var data: ZonedDateTime? = ZonedDateTime.now(),
                val text: String,
                var isDeleted: Boolean? = false): Serializable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
                }