package com.android.todo_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.todo_app.dao.Dao
import com.android.todo_app.database.Todo


//version change when change in schema

//delete table or update and so on
@Database(entities = [Todo::class], version = 3)
@TypeConverters(DateConverter::class)
abstract class MyDatabase: RoomDatabase() {

        abstract fun todoDao():Dao   //polymorphism

        companion object{
               private val DataBaseName="ToDo_Database"
               private var mydatabase:MyDatabase?=null

                //single object from database during entire life cycle of project  (singleton pattern)
                //create database as once
                fun getInstance(context:Context):MyDatabase{
                    var temp= mydatabase
                    if(temp==null) {
                       temp= Room.databaseBuilder(
                            context.applicationContext,
                            MyDatabase::class.java,
                            DataBaseName
                        ).fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build()
                    }
                    mydatabase=temp
                       return temp
                }


            //what about synchronized


        }

}