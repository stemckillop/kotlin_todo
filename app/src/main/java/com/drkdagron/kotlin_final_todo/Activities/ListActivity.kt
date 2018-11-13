package com.drkdagron.kotlin_final_todo.Activities

import android.animation.Animator
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import com.drkdagron.kotlin_final_todo.R
import com.drkdagron.kotlin_final_todo.db.Group
import com.drkdagron.kotlin_final_todo.db.Item
import com.drkdagron.kotlin_final_todo.db.tasks.AddItemToGroupTask
import com.drkdagron.kotlin_final_todo.db.tasks.GetItemsInGroupTask
import com.drkdagron.kotlin_final_todo.listeners.OnItemAdded
import com.drkdagron.kotlin_final_todo.listeners.OnItemDeleted
import com.drkdagron.kotlin_final_todo.listeners.OnItemListRetreived
import com.drkdagron.kotlin_final_todo.listeners.OnItemModified

class ListActivity : AppCompatActivity(), OnItemListRetreived, OnItemAdded, OnItemDeleted, OnItemModified {
    override fun onItemModified(index: Int, item: Item) {
        Log.e("TAG", item.item)
        (viewAdapter as GroupItemAdapter).data[index] = item
        viewAdapter.notifyDataSetChanged()
    }

    override fun onItemDeleted() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun onItemAdded(added: Item) {
        Log.e("TAG", "Item Added")
        closeAddView()
        (viewAdapter as GroupItemAdapter).data.add(added)
        viewAdapter.notifyDataSetChanged()
        addOpen = false
        itemTitle.setText("")
    }

    override fun onItemListRetreived(items: List<Item>) {
        Log.e("Size...", items.size.toString())
        (viewAdapter as GroupItemAdapter).data = items.toMutableList()
        viewAdapter.notifyDataSetChanged()
    }

    lateinit var group: Group
    lateinit var addView: View
    var addOpen : Boolean = false

    lateinit var itemTitle: EditText

    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        group = intent.getParcelableExtra("group_id") as Group

        viewManager = LinearLayoutManager(this)
        viewAdapter = GroupItemAdapter( this, arrayListOf(), group )
        recyclerView = findViewById<RecyclerView>(R.id.list_items).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }


        findViewById<TextView>(R.id.list_group_name).text = group.title
        addView = findViewById(R.id.list_add_parent)
        itemTitle = findViewById(R.id.list_item_add_text)
        itemTitle.setOnEditorActionListener { v, actionId, event ->

                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {

                        val item = Item(gid = group.gid!!, item = itemTitle.editableText.toString(), notes = "", tag = "")
                        val post = AddItemToGroupTask()
                        post.listen = this
                        post.execute(item)

                        return@setOnEditorActionListener true
                    }
                }
                return@setOnEditorActionListener false
        }

        closeAddView()

        val get = GetItemsInGroupTask()
        get.listen = this
        get.execute(group)
    }

    fun onAddViewClicked(view: View) {
        if (addOpen) {
            closeAddView()
            addOpen = false
        } else {
            openAddView()
            addOpen = true
        }
    }
    fun onCloseItemClicked(view: View) {
        closeAddView()
        itemTitle.setText("")
    }

    fun openAddView() {
        val ani = ObjectAnimator.ofFloat(addView, "translationY", 0f)
        ani.addListener(object: Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {
                addOpen = true
            }

            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}

        })
        ani.duration = 250
        ani.start()
    }
    fun closeAddView() {
        val ani = ObjectAnimator.ofFloat(addView, "translationY", resources.getDimension(R.dimen.addViewClose))
        ani.addListener(object: Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {
                addOpen = false
            }

            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}

        })
        ani.duration = 250
        ani.start()
    }
}
