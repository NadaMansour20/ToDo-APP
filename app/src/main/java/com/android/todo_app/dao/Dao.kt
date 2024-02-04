package com.android.todo_app.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.android.todo_app.database.Todo
import java.util.Date

//data access object
//all operation on data and access it
//annoutation processor implement fun
@Dao
interface Dao {
    @Insert
      fun add_todo(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Query("select * from todo")
    fun get_all_Todo(): MutableList<Todo>


    @Query("select * from todo where date=:date")
    fun getTodoByDate(date: Date): MutableList<Todo>

}