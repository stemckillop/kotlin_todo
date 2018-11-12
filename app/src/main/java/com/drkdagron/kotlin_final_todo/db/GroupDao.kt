package com.drkdagron.kotlin_final_todo.db

import android.arch.persistence.room.*

@Dao
interface GroupDao {
    @Query("SELECT * FROM itemgroup")
    fun getAll() : List<Group>

    @Update
    fun updateGroup(group: Group)

    @Insert
    fun insertGroup(group: Group)

    @Delete
    fun delete(group: Group)
    
}
