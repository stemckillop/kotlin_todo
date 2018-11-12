package com.drkdagron.kotlin_final_todo

import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import com.drkdagron.kotlin_final_todo.db.ListDB

class MyApplication: Application() {
    companion object {
        var database: ListDB? = null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("MyApplication", "Testing...")
        MyApplication.database = Room.databaseBuilder(this, ListDB::class.java, "list-todo-app").build()
    }
}