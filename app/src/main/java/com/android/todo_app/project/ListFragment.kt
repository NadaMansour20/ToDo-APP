package com.android.todo_app.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.todo_app.MyDatabase
import com.android.todo_app.R
import com.android.todo_app.clearTime
import java.util.Calendar

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    lateinit var recyclerView: RecyclerView
    var listadapter=ListAdapter(null)

    var calender=Calendar.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.taskrecycler)
        recyclerView.adapter=listadapter

    }


    override fun onResume() {
        super.onResume()
           //calender.time return time in millisecond
        getTodoListFromDB()
    }

    fun getTodoListFromDB(){

        //if(context==null)return
        val data=MyDatabase.getInstance(requireContext()).todoDao().getTodoByDate(calender.clearTime().time)
        listadapter.changeData(data.toMutableList())
    }


}