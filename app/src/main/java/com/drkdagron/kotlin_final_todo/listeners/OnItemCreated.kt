package com.drkdagron.kotlin_final_todo.listeners

import com.drkdagron.kotlin_final_todo.db.Group

interface OnItemCreated {
    fun onItemCreated(group: Group)
}