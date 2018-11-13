package com.drkdagron.kotlin_final_todo.db.tasks

import android.os.AsyncTask
import com.drkdagron.kotlin_final_todo.MyApplication
import com.drkdagron.kotlin_final_todo.db.Item
import com.drkdagron.kotlin_final_todo.listeners.OnItemModified

class ModifyItemTask : AsyncTask<Item, Void, Int > () {

    lateinit var listen : OnItemModified
    lateinit var item : Item
    var index: Int = 0

    override fun doInBackground(vararg params: Item?): Int {
        item = params[0]!!
        MyApplication.database!!.itemDao().updateItem(params[0]!!)
        return 0
    }

    override fun onPostExecute(result: Int?) {
        listen.onItemModified(index, item)
    }
}