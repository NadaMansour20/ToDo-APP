package com.android.todo_app.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            val addtodobuttonsheet = AddFragment()
            addtodobuttonsheet.show(supportFragmentManager," ")  //show button sheet

            //call back
            addtodobuttonsheet.OnTodoAddlisten=object :AddFragment.OnTodoAddlistener{
                override fun OnTodoAdd() {
                    //refresh database in list fragment

                    // if(listFragment.isVisible)  == (list fragment context =null)
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

