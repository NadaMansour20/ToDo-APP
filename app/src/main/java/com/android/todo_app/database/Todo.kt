package com.android.todo_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity( "TODO")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("ID")   //change column name
    var id:Int?=null,
    var name:String?=null,
    var detailes:String?=null,
    var data: Date? =null,
)
