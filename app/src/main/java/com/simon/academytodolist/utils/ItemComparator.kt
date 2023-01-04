package com.simon.academytodolist.utils

import com.simon.academytodolist.models.Item

enum class ItemComparator: Comparator<Item> {

    BY_DATE {
        override fun compare(x: Item, y: Item): Int {
            return -1 * x.data!!.compareTo(y.data)
        }
    },

    BY_TEXT_LENGTH {
        override fun compare(x: Item, y: Item): Int {
            return x.text.length.compareTo(y.text.length)
        }
    }

}