package com.android.todo_app.project

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.android.todo_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var bottomnavigation:BottomNavigationView
    lateinit var addbutton:FloatingActionButton
    var listFragment=ListFragment()
    //var settingsFragment=SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //to reset language AND mode after app closed
        val languageeeeeeeeee = LanguagePreferenceHelper.getLanguage(applicationContext)
        language(languageeeeeeeeee)
        //Log.e("languageeeeeeeeee", languageeeeeeeeee)

        val modeeeee = LanguagePreferenceHelper.getMode(applicationContext)
        if (modeeeee == 0)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)



        bottomnavigation = findViewById(R.id.bottomnavigation)
        addbutton = findViewById(R.id.floatingbutton)



        addbutton.setOnClickListener {
            val addtodobuttonsheet = AddFragment()
            addtodobuttonsheet.show(supportFragmentManager, " ")  //show button sheet

            //add call back
            addtodobuttonsheet.OnTodoAddlisten = object : AddFragment.OnTodoAddlistener {
                override fun OnTodoAdd() {
                    //refresh database in list fragment

                    // if(listFragment.isVisible)  == (list fragment context =null)
                    listFragment.getTodoListFromDB()
                }

            }
        }
        //update call back


        bottomnavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            if (it.itemId == R.id.list) {
                supportfragment(listFragment)     //save object

            } else if (it.itemId == R.id.settings) {
                supportfragment(SettingsFragment())
            }
            return@OnItemSelectedListener true
        })

    }

    fun supportfragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit()
    }


    //to change language should restart fragment
    fun restartFragment() {
        val intent = intent
        finish()
        startActivity(intent)

    }

    // to change language of application
    fun language(lang: String) {
        val res: Resources = resources
        val config: Configuration = res.configuration
        val dis: DisplayMetrics = res.displayMetrics
        val locale: Locale = Locale(lang)
        Locale.setDefault(locale)
        config.setLocale(locale)
        res.updateConfiguration(config, dis)

    }
}

