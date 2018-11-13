package com.drkdagron.kotlin_final_todo.Activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.drkdagron.kotlin_final_todo.R
import com.drkdagron.kotlin_final_todo.db.Group
import com.drkdagron.kotlin_final_todo.db.Item
import com.drkdagron.kotlin_final_todo.db.tasks.DeleteItemTask
import com.drkdagron.kotlin_final_todo.dialogs.ModifyItemDialog
import kotlinx.android.synthetic.main.list_group.view.*

class GroupItemAdapter(var ctx: Context, var data: MutableList<Item>, var owner: Group) :
        RecyclerView.Adapter<GroupItemAdapter.ViewHolder>() {

    class ViewHolder(val view: RelativeLayout) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context)
                .inflate(R.layout.list_item, p0, false) as RelativeLayout

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        if (data.get(p1).visible) {
            p0.view.visibility = View.VISIBLE
        } else {
            p0.view.visibility = View.GONE
        }

        p0.view.findViewById<ImageButton>(R.id.list_item_delete).setOnClickListener {
            AlertDialog.Builder(ctx)
                    .setTitle("Delete Item")
                    .setMessage("Are you sure you want to delete this item?")
                    .setPositiveButton("Yes") { i, d ->
                        var d = DeleteItemTask()
                        d.listen = (ctx as ListActivity)
                        d.execute(data.get(p1))
                        data.removeAt(p1)
                    }
                    .setNegativeButton("No") { i, d -> i.dismiss() }
                    .create().show()
        }
        p0.view.findViewById<ImageButton>(R.id.list_item_extras).setOnClickListener {
            var d = ModifyItemDialog()
            d.listen = ctx as ListActivity
            d.item = data.get(p1)
            d.index = p1
            d.show((ctx as ListActivity).supportFragmentManager, "modify_item")
        }

        p0.view.findViewById<TextView>(R.id.list_item_name).text = data.get(p1).item
        if (owner.tasksCanBeCompleted) {
            p0.view.findViewById<CheckBox>(R.id.list_item_check).visibility = View.VISIBLE
        } else {
            p0.view.findViewById<CheckBox>(R.id.list_item_check).visibility = View.GONE
        }
    }
}