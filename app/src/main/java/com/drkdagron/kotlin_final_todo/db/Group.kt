package com.drkdagron.kotlin_final_todo.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "itemGroup")
data class Group (
        @PrimaryKey(autoGenerate = true) var gid: Int? = null,
        var title: String,
        var items: Int,
        var tag: String)