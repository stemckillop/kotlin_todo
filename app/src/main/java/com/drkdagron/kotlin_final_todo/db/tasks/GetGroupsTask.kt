package com.drkdagron.kotlin_final_todo.db.tasks

import android.os.AsyncTask
import android.util.Log
import com.drkdagron.kotlin_final_todo.MyApplication
import com.drkdagron.kotlin_final_todo.db.Group
import com.drkdagron.kotlin_final_todo.listeners.OnListReceived

class GetGroupsTask(var listen: OnListReceived) : AsyncTask<Void, Void, List<Group>>() {

    override fun doInBackground(vararg params: Void?): List<Group> {
        Log.e("Size...", "testing")
        return MyApplication.database!!.groupDao().getAll()
    }

    override fun onPostExecute(result: List<Group>?) {
        listen.onListReceived(result!!)
    }
}