package com.shocker.mongo_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

class uploadData : AppCompatActivity() {
    lateinit var userdata:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_data)
        userdata=findViewById(R.id.et5)
    }



}