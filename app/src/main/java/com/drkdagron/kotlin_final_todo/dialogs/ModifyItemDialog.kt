package com.drkdagron.kotlin_final_todo.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.app.ListActivity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.drkdagron.kotlin_final_todo.R
import com.drkdagron.kotlin_final_todo.db.Item
import com.drkdagron.kotlin_final_todo.db.tasks.AddItemToGroupTask
import com.drkdagron.kotlin_final_todo.db.tasks.ModifyItemTask
import com.drkdagron.kotlin_final_todo.listeners.OnItemModified

class ModifyItemDialog : DialogFragment() {

    lateinit var item: Item
    var index: Int = -1
    lateinit var listen: OnItemModified

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var v = LayoutInflater.from(context).inflate(R.layout.dialog_modify_item, null)

        var edit = v.findViewById<EditText>(R.id.dialog_modify_item_title)
        edit.setText(item.item)
        edit.setOnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    event != null &&
                    event.getAction() == KeyEvent.ACTION_DOWN &&
                    event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                if (event == null || !event.isShiftPressed()) {

                    item.item = edit.editableText.toString()

                    return@setOnEditorActionListener true
                }
            }
            return@setOnEditorActionListener false
        }

        var d = AlertDialog.Builder(context)
                .setTitle("Modify Item")
                .setView(v)
                .setPositiveButton("Done") { i, d ->
                    val post = ModifyItemTask()
                    item.item = edit.editableText.toString()
                    post.index = index
                    post.listen = listen
                    post.execute(item)
                }
                .setNegativeButton("Cancel") {
                    i, d -> i.dismiss()
                }
                .create()
        return d
    }
}