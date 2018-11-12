package com.drkdagron.kotlin_final_todo.db

import android.arch.persistence.room.*

@Dao
interface ItemDao {
    @Query ("SELECT * FROM item")
    fun getItems() : List<Item>

    @Query ("SELECT * FROM item WHERE gid = :gid")
    fun getItemsInGroup(gid: Int) : List<Item>

    @Insert
    fun insertItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)
}
