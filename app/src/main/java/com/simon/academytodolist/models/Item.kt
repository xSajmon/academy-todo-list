package com.simon.academytodolist.models

import java.io.Serializable
import java.time.ZonedDateTime

data class Item(val id: Int,
                var data: ZonedDateTime? = ZonedDateTime.now(),
                val text: String,
                var isDeleted: Boolean? = false): Serializable