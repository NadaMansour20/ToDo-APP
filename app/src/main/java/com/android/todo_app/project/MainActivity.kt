package com.android.todo_app.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.android.todo_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var bottomnavigation:BottomNavigationView
    lateinit var addbutton:FloatingActionButton
    var listFragment=ListFragment()
    //var settingsFragment=SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomnavigation=findViewById(R.id.bottomnavigation)
        addbutton=findViewById(R.id.floatingbutton)



        addbutton.setOnClickListener {
            var addtodobuttonsheet=AddFragment()
            addtodobuttonsheet.show(supportFragmentManager," ")  //show button sheet

            addtodobuttonsheet.OnTodoAddlisten=object :AddFragment.OnTodoAddlistener{
                override fun OnTodoAdd() {
                    //refresh database in list fragment

                    if(listFragment.isVisible)
                    listFragment.getTodoListFromDB()
                }

            }
        }
        bottomnavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            if(it.itemId == R.id.list) {
               supportfragment(listFragment)     //save object

            } else if(it.itemId == R.id.settings) {
                supportfragment(SettingsFragment())
            }
            return@OnItemSelectedListener true
        })


    }

    fun supportfragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.framelayout,fragment).commit()
    }
}

