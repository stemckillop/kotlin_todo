package com.drkdagron.kotlin_final_todo.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "itemGroup")
data class Group (
        @PrimaryKey(autoGenerate = true) var gid: Int? = null,
        var title: String,
        var items: Int,
        var tag: String,
        var showDate: Boolean = false,
        var tasksCanBeCompleted: Boolean = false,
        var hideCompleted: Boolean = false) : Parcelable

//settings -> characters determine