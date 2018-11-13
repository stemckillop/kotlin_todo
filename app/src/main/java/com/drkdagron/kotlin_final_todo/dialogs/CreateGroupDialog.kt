package com.drkdagron.kotlin_final_todo.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Switch
import com.drkdagron.kotlin_final_todo.R
import com.drkdagron.kotlin_final_todo.db.Group
import com.drkdagron.kotlin_final_todo.db.tasks.CreateGroupTask
import com.drkdagron.kotlin_final_todo.listeners.OnItemCreated

class CreateGroupDialog() : DialogFragment() {

    lateinit var listener: OnItemCreated

    lateinit var tag: EditText
    lateinit var title: EditText
    lateinit var showDate: Switch
    lateinit var canBeCompleted: Switch
    lateinit var hideCompleted: Switch

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v = LayoutInflater.from(context).inflate(R.layout.dialog_create_group, null)

        tag = v.findViewById(R.id.create_group_tag)
        title = v.findViewById(R.id.create_group_title)
        showDate = v.findViewById(R.id.create_group_show_date)
        canBeCompleted = v.findViewById(R.id.create_group_tasks_completed)
        hideCompleted = v.findViewById(R.id.create_group_hide_completed)

        val b = AlertDialog.Builder(context)
                .setView(v)
                .setTitle("Create Item Group")
                .setPositiveButton("Create"){_, _ ->

                    val gp = Group(tag = tag.editableText.toString(), title = title.editableText.toString(), items = 0, showDate = showDate.isChecked,
                            tasksCanBeCompleted = canBeCompleted.isChecked, hideCompleted = hideCompleted.isChecked)
                    val groupTask = CreateGroupTask(listener, gp)
                    groupTask.execute()

                }
                .setNegativeButton("Cancel", {dialog, which -> dialog.dismiss() })
                .create()

        return b
    }
}