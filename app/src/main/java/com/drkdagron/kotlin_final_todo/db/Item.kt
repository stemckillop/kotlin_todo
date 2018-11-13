package com.drkdagron.kotlin_final_todo.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Item (
        @PrimaryKey(autoGenerate = true) var id: Int? = null,
        var item: String,
        var tag: String,
        var gid: Int,
        var notes: String,
        var done: Boolean = false,
        var visible: Boolean = true)