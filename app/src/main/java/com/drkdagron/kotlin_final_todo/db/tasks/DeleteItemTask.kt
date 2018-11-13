package com.drkdagron.kotlin_final_todo.db.tasks

import android.os.AsyncTask
import com.drkdagron.kotlin_final_todo.MyApplication
import com.drkdagron.kotlin_final_todo.db.Item
import com.drkdagron.kotlin_final_todo.listeners.OnItemDeleted

class DeleteItemTask : AsyncTask<Item, Void, Int>() {

    lateinit var listen: OnItemDeleted

    override fun doInBackground(vararg params: Item?): Int {
        MyApplication.database!!.itemDao().deleteItem(params[0]!!)
        return 0
    }

    override fun onPostExecute(result: Int?) {
        listen.onItemDeleted()
    }
}