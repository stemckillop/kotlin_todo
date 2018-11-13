package com.drkdagron.kotlin_final_todo.listeners

import com.drkdagron.kotlin_final_todo.db.Item

interface OnItemListRetreived {
    fun onItemListRetreived(items: List<Item>)
}