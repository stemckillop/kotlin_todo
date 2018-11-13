package com.drkdagron.kotlin_final_todo.db.tasks

import android.os.AsyncTask
import com.drkdagron.kotlin_final_todo.MyApplication
import com.drkdagron.kotlin_final_todo.db.Item
import com.drkdagron.kotlin_final_todo.listeners.OnItemAdded

class AddItemToGroupTask : AsyncTask<Item, Void, Item >() {

    lateinit var listen : OnItemAdded

    override fun doInBackground(vararg params: Item?): Item {
        MyApplication.database!!.itemDao().insertItem(params[0]!!)
        return params[0]!!
    }

    override fun onPostExecute(result: Item?) {
        listen.onItemAdded(result!!)
    }
}