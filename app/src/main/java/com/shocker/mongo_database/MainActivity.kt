package com.shocker.mongo_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    val appid:String="mongodbapp-cpytv"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login: Button = findViewById(R.id.button)
        val logout: Button = findViewById(R.id.button2)
    }


}