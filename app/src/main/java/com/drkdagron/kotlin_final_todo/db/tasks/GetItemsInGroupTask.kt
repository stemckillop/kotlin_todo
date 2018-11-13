package com.drkdagron.kotlin_final_todo.db.tasks

import android.os.AsyncTask
import com.drkdagron.kotlin_final_todo.MyApplication
import com.drkdagron.kotlin_final_todo.db.Group
import com.drkdagron.kotlin_final_todo.db.Item
import com.drkdagron.kotlin_final_todo.listeners.OnItemListRetreived

class GetItemsInGroupTask : AsyncTask<Group, Void, List<Item> >() {

    lateinit var listen : OnItemListRetreived

    override fun doInBackground(vararg params: Group?): List<Item> {
        return MyApplication.database!!.itemDao().getItemsInGroup(params[0]!!.gid!!)
    }

    override fun onPostExecute(result: List<Item>?) {
        listen.onItemListRetreived(result!!)
    }
}