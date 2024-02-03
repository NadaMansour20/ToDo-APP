package com.android.todo_app.project

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.todo_app.MyDatabase
import com.android.todo_app.R
import com.android.todo_app.clearTime
import com.android.todo_app.database.Todo
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.Calendar

class ListFragment : Fragment() {

    var textisdone: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    lateinit var recyclerView: RecyclerView
    var listadapter = ListAdapter(null)
    lateinit var materialcalenderview: MaterialCalendarView


    var calender = Calendar.getInstance()

    lateinit var todolistdata: MutableList<Todo>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.taskrecycler)
        recyclerView.adapter = listadapter

        initattribute()
        deleteAndupdate()

    }


    override fun onResume() {
        super.onResume()

        //getTodoListFromDB()
        getalltodo()
    }

    fun getalltodo() {
        if (context == null) return
        todolistdata = MyDatabase.getInstance(requireContext()).todoDao()
            .get_all_Todo()
        listadapter.changeData(todolistdata)
    }

    fun getTodoListFromDB() {

        //if list fragment is not visible return
        if (context == null) return
        todolistdata = MyDatabase.getInstance(requireContext()).todoDao()
            .getTodoByDate(calender.clearTime().time) //calender.time return time in millisecond and clearTime() used to delete millisecond
        listadapter.changeData(todolistdata)
    }

    fun initattribute() {

        textisdone = requireView().findViewById(R.id.text_isdone)
        recyclerView = requireView().findViewById(R.id.taskrecycler)
        materialcalenderview = requireView().findViewById(R.id.calendarView)
        materialcalenderview.selectedDate = CalendarDay.today()
        recyclerView.adapter = listadapter
        materialcalenderview.setOnDateChangedListener { widget, calenderDay, selected ->
            calender.set(Calendar.DAY_OF_MONTH, calenderDay.day)
            calender.set(Calendar.MONTH, calenderDay.month - 1)
            calender.set(Calendar.YEAR, calenderDay.year)
            getTodoListFromDB()
        }
    }

    fun deleteAndupdate() {/////////////////////////
        listadapter.itemclickedDeleteAndUpdate = object : ListAdapter.todoItemclicked {
            override fun DeletedItem(position: Int, todo: Todo) {

                //return todo before remove or take a parameter
                // val todo=todolistdata[position]

                todolistdata.removeAt(position)
                listadapter.notifyDataSetChanged()
                listadapter.notifyItemRemoved(position)

                MyDatabase.getInstance(requireContext()).todoDao().delete(todo)
                Toast.makeText(requireContext(), "ToDo Deleted", Toast.LENGTH_LONG).show()

            }

            override fun UpdateItem(position: Int, todo: Todo) {
                val intent = Intent(requireContext(), UpdateActivity::class.java)

                // to make intent with object should implement Serializable on todo or class object
                intent.putExtra("todo", todo)
                startActivity(intent)

            }

            override fun Done(done: ImageView, taskname: TextView) {
                taskname.setTextColor(Color.parseColor("#0F9D58"))

                //to remove image from image view
                done.setImageDrawable(null)

                //convert done image view background to transparent color to show Done!!
                done?.setBackgroundResource(R.color.transparent)


            }


        }
    }
}