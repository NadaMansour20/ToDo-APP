package com.android.todo_app.project

import android.content.res.Configuration
import android.content.res.Resources
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


    lateinit var datalanguage: Array<String>
    lateinit var datamode: Array<String>
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


        // get spinner items from resource.string
        datalanguage = arrayOf(
            getString(R.string.select_language),
            getString(R.string.arabic),
            getString(R.string.english)
        )
        datamode = arrayOf(
            getString(R.string.select_mode),
            getString(R.string.dark),
            getString(R.string.light)
        )


        fillspinner(spinner = spinnerlanguage, items = datalanguage)
        fillspinner(spinner = spinnermode, items = datamode)


        // change language
        spinnerlanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                if (position == 1) {
                    language("ar")

                }
                if (position == 2) {
                    language("en")


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
    fun fillspinner(spinner: Spinner, items: Array<String>) {
        val adapter_spinner = ArrayAdapter(
            requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item, items
        )
        spinner.adapter = adapter_spinner


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

        // casting requireActivity of fragment as activity and restart it
        (requireActivity() as MainActivity).restartFragment()


    }


}
















