package com.android.todo_app.project

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.android.todo_app.R
import java.util.Locale

class SettingsFragment : Fragment() {

    var datalanguage= arrayOf("Selected Language","Arabic","English")
    var datamode= arrayOf("Selected Mode","Dark","Light")

    lateinit var spinnerlanguage: Spinner
    lateinit var spinnermode: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinnerlanguage = view.findViewById(R.id.spinnerlanguage)
        spinnermode = view.findViewById(R.id.sppinermode)


        fillspinner(spinner = spinnerlanguage, items = datalanguage)
        fillspinner(spinner = spinnermode, items = datamode)

        // change language
        spinnerlanguage.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position==1){
                    language("ar")
                    datalanguage[0]="أختر اللغه"
                    datalanguage[1]="عربي"
                    datalanguage[2]="انجليزي"

                    datamode[0]="أختر الوضع"
                    datamode[1]="داكن"
                    datamode[2]="فاتح"

                }
                if(position==2){
                    language("en")
                    datalanguage[0]="Select Language"
                    datalanguage[1]="Arabic"
                    datalanguage[2]="English"

                    datamode[0]="Selected Mode"
                    datamode[1]="Dark"
                    datamode[2]="Light"
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        // change mode
        spinnermode.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position==1){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                if(position==2){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }
    // to fill data in spinner
    fun fillspinner(spinner:Spinner,items:Array<String>){
        var adapter_spinner= ArrayAdapter(requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,items)
        spinner.adapter=adapter_spinner
    }


    fun language(lang:String){
        var res:Resources=this.resources
        var config:Configuration=res.configuration
        var dis:DisplayMetrics=res.displayMetrics
        var locale:Locale= Locale(lang)
        Locale.setDefault(locale)
        config.setLocale(locale)
        res.updateConfiguration(config,dis)


    }
}
















