package com.shocker.mongo_database

import android.content.Intent
import android.opengl.GLUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import kotlinx.coroutines.runBlocking
import javax.security.auth.callback.Callback

class RegisterUser : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var pass:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
       email=findViewById(R.id.et1)
        pass=findViewById(R.id.et2)
        val register:Button=findViewById(R.id.button3)
        register.setOnClickListener {
            registeruser()
        }
        val gotologin:Button=findViewById(R.id.button4)
        gotologin.setOnClickListener {
            val intent=Intent(this,LoginUser::class.java)
            startActivity(intent)
        }

    }

    fun registeruser(){
        Realm.init(this)
        val app:App=App(AppConfiguration.Builder("mongodbapp-cpytv").build())
        val emaildata=email.text.toString()
        val passdata=pass.text.toString()
        app.emailPassword.registerUserAsync(emaildata,passdata, App.Callback {


            if(it.isSuccess){
                Toast.makeText(this,"user registered successfully",Toast.LENGTH_LONG).show()
            }
            else{
                val errormessage=it.error.errorMessage.toString()
                //here the error message consist of everytype of error in it like if the user already exist then it would handle it own its own then if the length of password is not
                //satifisifed then also it would handle the error and notify the user what is the problem due to which registration failed.
                Toast.makeText(this,errormessage,Toast.LENGTH_LONG).show()
            }

        })
    }
    }


