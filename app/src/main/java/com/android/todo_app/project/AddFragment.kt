package com.android.todo_app.project

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.android.todo_app.MyDatabase
import com.android.todo_app.R
import com.android.todo_app.clearTime
import com.android.todo_app.database.Todo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class AddFragment:BottomSheetDialogFragment() {

    lateinit var addtask:TextInputLayout
    lateinit var adddetails:TextInputLayout
    lateinit var choosedata:TextView
    lateinit var add:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initattribute()
    }


    // get object from calender
    var calender=Calendar.getInstance()


    fun validate():Boolean{
        var valid=true
        if(addtask.editText?.text.toString().isBlank()) {
            addtask.error = "please enter task "
            valid = false
        }
        else addtask.error=null

        if(adddetails.editText?.text.toString().isBlank()) {
            adddetails.error = "please enter details"
            valid = false
        }
        else  adddetails.error=null

        return valid
    }



    //view--->nullable
    //requireView--->not nullable   and so on
    fun initattribute(){
        addtask=requireView().findViewById(R.id.entertask)
        adddetails=requireView().findViewById(R.id.enterdetailes)
        add=requireView().findViewById(R.id.addtodo)
        choosedata=requireView().findViewById(R.id.choosedata)

        choosedata.setOnClickListener {
            addDatePicker()
        }

        add.setOnClickListener {
            if(validate()){
                var task=addtask.editText?.text.toString()
                var details=adddetails.editText?.text.toString()

                insertdata(task=task,details=details)
            }

        }


    }

// insert data into database
    fun insertdata(task:String,details:String){

        var todo=Todo(
            name = task, detailes =details, data =calender.clearTime().time
        )
        MyDatabase.getInstance(requireContext().applicationContext).todoDao().add_todo(todo)

    OnTodoAddlisten?.OnTodoAdd()

    dismiss()   //end fragment


    }

    //call back
    var OnTodoAddlisten:OnTodoAddlistener?=null

    interface OnTodoAddlistener{
        fun OnTodoAdd()
    }


    // open datapicker
    fun addDatePicker(){

        var datepicker=DatePickerDialog(requireContext(),object :DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                choosedata.setText(" "+dayOfMonth+"/"+(month+1)+"/"+" "+year)

            }
        },calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH))

        datepicker.show()
    }




}