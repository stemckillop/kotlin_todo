package com.drkdagron.kotlin_final_todo.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database (entities = arrayOf(Item::class, Group::class), version = 1)
abstract class ListDB : RoomDatabase() {
    abstract fun itemDao() : ItemDao
    abstract fun groupDao() : GroupDao
}
