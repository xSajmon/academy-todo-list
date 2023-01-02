package com.simon.academytodolist

import java.io.Serializable
import java.time.ZonedDateTime

data class Item(val id: Int,
                val data: ZonedDateTime? = ZonedDateTime.now(),
                val text: String,
                var isDeleted: Boolean? = false): Serializable