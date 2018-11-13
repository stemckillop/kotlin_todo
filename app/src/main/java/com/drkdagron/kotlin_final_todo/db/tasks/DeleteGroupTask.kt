package com.drkdagron.kotlin_final_todo.db.tasks

import android.os.AsyncTask
import com.drkdagron.kotlin_final_todo.db.Group
import com.drkdagron.kotlin_final_todo.listeners.OnGroupDeleted

class DeleteGroupTask : AsyncTask<Group, Void, Void > () {

    lateinit var listen : OnGroupDeleted

    override fun doInBackground(vararg params: Group?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostExecute(result: Void?) {
        listen.onGroupDeleted()
    }
}