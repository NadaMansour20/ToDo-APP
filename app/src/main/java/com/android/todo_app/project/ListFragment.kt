package com.android.todo_app.project

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.todo_app.MyDatabase
import com.android.todo_app.R
import com.android.todo_app.database.Todo
import com.daimajia.swipe.SwipeLayout
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class ListFragment : Fragment() {

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
    var textisdone: TextView? = null
    var swipeLayout: SwipeLayout? = null


    var calender = Calendar.getInstance()

    lateinit var todolistdata: MutableList<Todo>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.taskrecycler)
        recyclerView.adapter = listadapter
        swipeLayout = view.findViewById(R.id.swipe_layout)

        textisdone = view.findViewById(R.id.text_isdone)
        initattribute()
        deleteAndupdate()


    }


    override fun onResume() {
        super.onResume()

        getTodoListFromDB()
        //getalltodo()
    }

    fun getalltodo() {
        if (context == null) return
        todolistdata = MyDatabase.getInstance(requireContext()).todoDao()
            .get_all_Todo()
        listadapter.changeData(todolistdata)
    }

    fun getTodoListFromDB() {

        // store date without minutes ar seconds or hours because if i don't it ,task is stored by hours not day

        val dateformat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val Dateafterformat: Date = dateformat.parse(dateformat.format(calender.time))


        //if list fragment is not visible return
        if (context == null) return
        todolistdata = MyDatabase.getInstance(requireContext()).todoDao()
            .getTodoByDate(Dateafterformat)

        Log.d("date is ", Dateafterformat.toString())
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

                if (todo.is_done == true) {
                    Toast.makeText(
                        requireContext(),
                        "** ToDo Done Successfully **",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else
                    Toast.makeText(requireContext(), "ToDo Deleted", Toast.LENGTH_LONG).show()

            }

            override fun UpdateItem(position: Int, todo: Todo) {
                val intent = Intent(requireContext(), UpdateActivity::class.java)

                // to make intent with object should implement Serializable on todo or class object
                intent.putExtra("todo", todo)
                startActivity(intent)

            }


            override fun Done(
                todo: Todo,
                iconphoto_delete: ImageView,
                done: ImageView,
                taskname: TextView,
                task_isdone: TextView
            ) {

                val todo = Todo(todo.id, todo.name, todo.detailes, todo.date, true)
                MyDatabase.getInstance(requireContext().applicationContext).todoDao().update(todo)

                if (todo.is_done == true) {
                    taskname.setTextColor(Color.parseColor("#0F9D58"))

                    //to make animation between image view disappear and text view appear
                    //In two ways

                    //task_isdone.alpha = 1.0f
                    //done.alpha = 0.0f

                    //OR

                    task_isdone.isVisible = true
                    done.isVisible = false
                }


            }


        }

    }


}