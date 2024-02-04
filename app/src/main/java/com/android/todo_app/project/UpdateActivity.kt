package com.android.todo_app.project

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.todo_app.MyDatabase
import com.android.todo_app.R
import com.android.todo_app.clearTime
import com.android.todo_app.database.Todo
import com.google.android.material.textfield.TextInputLayout
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar


class UpdateActivity : AppCompatActivity() {

    var updatetask: TextInputLayout? = null
    var updatedetails: TextInputLayout? = null
    var updatetime: TextView? = null
    var buttonsave: Button? = null
    lateinit var datafromintent: Todo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)


        initialAttribute()
        RecieveDatafromIntent()
    }

    var calender = Calendar.getInstance()

    fun validate(): Boolean {
        var valid = true
        if (updatetask?.editText?.text.toString().isBlank()) {
            updatetask?.error = "please enter task "
            valid = false
        } else updatetask?.error = null

        if (updatedetails?.editText?.text.toString().isBlank()) {
            updatedetails?.error = "please enter details"
            valid = false
        } else updatedetails?.error = null

        return valid
    }


    fun RecieveDatafromIntent() {
        datafromintent = intent.getSerializableExtra("todo") as Todo

        updatetask?.editText?.setText(datafromintent.name)
        updatedetails?.editText?.setText(datafromintent.detailes)


        // date format from Tue Apr 26 09:14:10 GMT+02:00 2016 to 26/4/2016

        val dateformat: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val Dateafterformat: String = dateformat.format(datafromintent.date)

        updatetime?.setText(Dateafterformat)


    }

    fun initialAttribute() {
        updatetask = findViewById(R.id.updatetask)
        updatedetails = findViewById(R.id.updatedetails)
        updatetime = findViewById(R.id.updatetime)
        buttonsave = findViewById(R.id.buttonsave)


        updatetime?.setOnClickListener {
            addDatePicker()
        }

        buttonsave?.setOnClickListener {
            if (validate()) {
                val task = updatetask?.editText?.text.toString()
                val details = updatedetails?.editText?.text.toString()
                val time = Calendar.getInstance().clearTime().time

                val recievetodo = Todo(datafromintent.id, task, details, time, false)

                updatedata(recievetodo)
            }

        }
    }

    fun updatedata(recievetodo: Todo) {

        MyDatabase.getInstance(this).todoDao().update(recievetodo)
        Toast.makeText(this, "ToDo Updated", Toast.LENGTH_LONG).show()
        onBackPressed() //remove activity and back to previous activity
    }

    fun addDatePicker() {

        val datepicker = DatePickerDialog(
            this,
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    calender.set(Calendar.YEAR, year)
                    calender.set(Calendar.MONTH, month + 1)
                    calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    updatetime?.setText(" " + dayOfMonth + "/" + (month + 1) + "/" + " " + year)

                }
            },
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )

        datepicker.show()
    }


}