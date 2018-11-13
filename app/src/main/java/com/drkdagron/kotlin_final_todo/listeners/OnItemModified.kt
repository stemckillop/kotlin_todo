package com.drkdagron.kotlin_final_todo.listeners

import com.drkdagron.kotlin_final_todo.db.Item

interface OnItemModified {
    fun onItemModified(index: Int, item: Item)
}