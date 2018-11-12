package com.drkdagron.kotlin_final_todo.Activities

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.drkdagron.kotlin_final_todo.R
import com.drkdagron.kotlin_final_todo.db.Group
import kotlinx.android.synthetic.main.list_group.view.*

class GroupListAdapter(var data: MutableList<Group>) :
        RecyclerView.Adapter<GroupListAdapter.ViewHolder>() {

    class ViewHolder(val view: RelativeLayout) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder{
        val view = LayoutInflater.from(p0.context)
                .inflate(R.layout.list_group, p0, false) as RelativeLayout

        view.findViewById<ImageButton>(R.id.list_group_extra).setOnClickListener {
            Toast.makeText(p0.context, "Menu button clicked", Toast.LENGTH_SHORT).show()
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.view.findViewById<TextView>(R.id.list_group_title).text = data.get(p1).title
    }
}