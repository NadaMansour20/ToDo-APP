package com.android.todo_app.project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.android.todo_app.R

class Splash:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(this@Splash, MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}