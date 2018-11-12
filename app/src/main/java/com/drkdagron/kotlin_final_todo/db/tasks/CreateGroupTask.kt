package com.drkdagron.kotlin_final_todo.db.tasks

import android.os.AsyncTask
import com.drkdagron.kotlin_final_todo.MyApplication
import com.drkdagron.kotlin_final_todo.listeners.OnItemCreated
import com.drkdagron.kotlin_final_todo.db.Group

class CreateGroupTask(val listen: OnItemCreated, val group: Group) : AsyncTask<Group, Void, Group>() {

    override fun doInBackground(vararg params: Group?): Group? {
        MyApplication.database!!.groupDao().insertGroup(group)
        return group
    }

    override fun onPostExecute(result: Group?) {
        listen.onItemCreated(result!!)
    }
}