package com.drkdagron.kotlin_final_todo.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.drkdagron.kotlin_final_todo.R
import com.drkdagron.kotlin_final_todo.R.attr.layoutManager
import com.drkdagron.kotlin_final_todo.db.Group
import com.drkdagron.kotlin_final_todo.db.tasks.GetGroupsTask
import com.drkdagron.kotlin_final_todo.dialogs.CreateGroupDialog
import com.drkdagron.kotlin_final_todo.listeners.OnItemCreated
import com.drkdagron.kotlin_final_todo.listeners.OnListReceived

class MainActivity : AppCompatActivity(), OnItemCreated, OnListReceived {

    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager

    override fun onListReceived(groups: List<Group>) {
        Log.e("Size...", groups.size.toString())
        (viewAdapter as GroupListAdapter).data = groups.toMutableList()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onItemCreated(group: Group) {
        (viewAdapter as GroupListAdapter).data.add(group)
        viewAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = GroupListAdapter( arrayListOf() )
        recyclerView = findViewById<RecyclerView>(R.id.main_list_groups).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val get = GetGroupsTask(this)
        get.execute()

    }

    fun createGroupDialog(view: View) {
        var cgd = CreateGroupDialog()
        cgd.listener = this
        cgd.show(supportFragmentManager, "creategroupdialog")
    }
}
