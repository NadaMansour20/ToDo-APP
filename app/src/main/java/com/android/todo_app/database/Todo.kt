package com.android.todo_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity("TODO")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("ID")   //change column name
    var id: Int,
    var name: String? = null,
    var detailes: String? = null,
    var date: Date? = null,
    var is_done: Boolean? = false
) : Serializable     // to make intent with object should implement Serializable on todo or class object

